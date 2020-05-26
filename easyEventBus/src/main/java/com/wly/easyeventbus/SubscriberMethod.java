package com.wly.easyeventbus;

import java.lang.reflect.Method;

/**
 * author: wangliyun
 * date: 2020/5/26
 * description: 注册类中的注册方法信息
 */
public class SubscriberMethod {

    // 注册方法
    private Method method;

    // 线程类型
    private ThreadModel threadModel;


    private Class<?> eventType;

    public SubscriberMethod(Method method, ThreadModel threadModel, Class<?> parameterType) {
        this.method = method;
        this.threadModel = threadModel;
        this.eventType = parameterType;
    }


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadModel getThreadModel() {
        return threadModel;
    }

    public void setThreadModel(ThreadModel threadModel) {
        this.threadModel = threadModel;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }
}
