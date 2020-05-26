package com.wly.easyeventbus;

import android.os.Handler;
import android.os.Looper;

import com.wly.easyeventbus.annotation.Subscriber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: wangliyun
 * date: 2020/5/26
 * description:
 */
public class EventBus {
    private static EventBus instance = new EventBus();
    private Map<Object, List<SubscriberMethod>> cacheMap;
    private Handler handler;
    private ExecutorService executorService;

    private EventBus() {
        cacheMap = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newCachedThreadPool();
    }

    public static EventBus getDefault() {
        return instance;
    }

    public void register(Object subscriber) {
        Class<?> aClass = subscriber.getClass();
        List<SubscriberMethod> subscriberMethods = cacheMap.get(subscriber);

        // 如果已经注册就不需要注册
        if (subscriberMethods == null) {
            subscriberMethods = getSubscriberMethod(subscriber);
            cacheMap.put(subscriber, subscriberMethods);
        }
    }

    //遍历能够接收事件的方法
    private List<SubscriberMethod> getSubscriberMethod(Object subscriber) {
        List<SubscriberMethod> list = new ArrayList<>();

        Class<?> aClass = subscriber.getClass();

        // 需要 ---- subscriber---> BaseActivity ---> Activity
        while (aClass != null) {

            // 判断父类是在哪个包下，（如果是系统的就不需要）
            String name = aClass.getName();
            if (name.startsWith("java.") ||
                    name.startsWith("javax.") ||
                    name.startsWith("android.") ||
                    name.startsWith("androidx.")) {
                break;
            }
            Method[] declaredMethod = aClass.getDeclaredMethods();

            for (Method method : declaredMethod) {
                Subscriber annotation = method.getAnnotation(Subscriber.class);
                if (annotation == null) {
                    continue;
                }
                //检测订阅接收消息方法参数是否合格
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException("eventBus only Support single Param");
                }

                //符合要求

                ThreadModel threadModel = annotation.threadMode();

                SubscriberMethod subscriberMethod = new SubscriberMethod(method, threadModel, parameterTypes[0]);

                list.add(subscriberMethod);

            }


            aClass = aClass.getSuperclass();

        }
        return list;
    }

    public void unregister(Object subscriber) {
        Class<?> aClass = subscriber.getClass();

        List<SubscriberMethod> subscriberMethods = cacheMap.get(subscriber);

        if (subscriberMethods != null) {
            cacheMap.remove(subscriber);
        }
    }


    public void post(final Object obj) {
        Set<Object> set = cacheMap.keySet();

        Iterator<Object> iterator = set.iterator();

        while (iterator.hasNext()) {
            // 拿到注册类
            final Object next = iterator.next();
            // 获取注册类中所有添加注解的方法
            List<SubscriberMethod> subscriberMethods = cacheMap.get(next);


            for (final SubscriberMethod subscriberMethod : subscriberMethods) {
                // 判断这个方法是否应该接收事件
                if (subscriberMethod.getEventType().isAssignableFrom(obj.getClass())) {
                    switch (subscriberMethod.getThreadModel()) {
                        case MAIN:
                            //如果接收方法在主线程执行的清空
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(subscriberMethod, next, obj);
                            } else {

                                //post 方法执行在子线程中，接收消息在主线程
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscriberMethod, next, obj);
                                    }
                                });
                            }
                            break;

                        //接收方法在子线程的情况
                        case ASYNC:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscriberMethod, next, obj);
                                    }
                                });
                            } else {
                                invoke(subscriberMethod, next, obj);
                            }
                            break;
                        case POSTING:
                            break;
                    }

                }
            }

        }

    }

    private void invoke(SubscriberMethod subscriberMethod, Object next, Object obj) {
        Method method = subscriberMethod.getMethod();
        try {
            method.invoke(next, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
