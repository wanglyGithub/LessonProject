package com.wly.baselib.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.wly.baselib.base.impl.IView

interface IBasePresenter<V: IView>:LifecycleObserver {

    fun onAttachView(view:V)

    fun onDetachView()


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner)
}