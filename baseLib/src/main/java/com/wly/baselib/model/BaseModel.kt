package com.wly.baselib.model

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.wly.baselib.utils.UILogUtil

open class BaseModel : IModel,LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner){
        UILogUtil.d(message = "BaseModel onDestroy（）")
        owner.lifecycle.removeObserver(this)
    }
}