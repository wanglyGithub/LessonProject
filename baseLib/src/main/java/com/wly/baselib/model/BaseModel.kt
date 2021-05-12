package com.wly.baselib.model

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.wly.baselib.utils.UILogUtil

open class BaseModel : IModel, LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner){
        UILogUtil.d(message = "BaseModel onDestroy（）")
        owner.lifecycle.removeObserver(this)
    }
}