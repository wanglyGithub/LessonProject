package com.wly.baselib.presenter;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

/**
 * @Description: BasePresenter 接口
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 11:53
 * @Version: 1.0
 */
public interface IBasePresenter<V> extends LifecycleObserver {

    /**
     * 绑定View
     * 目前 支持activity和fragment
     *
     * @param view view
     */
    void onAttach(V view);


    /**
     * 对应生命周期onDetach方法
     */
    void onDetach();


    /**
     * 获取当前绑定到presenter的对象
     *
     * @return V
     */
    V getView();


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NonNull LifecycleOwner owner);

}
