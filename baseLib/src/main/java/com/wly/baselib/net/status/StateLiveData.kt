package com.wly.baselib.net.status

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.wly.baselib.net.bean.BaseResponse
import com.wly.baselib.net.dsl.ResultCallback

/**
 * author: wanglyGithub
 * date: 2021/8/13
 * description:
 */
class StateLiveData<T> : MutableLiveData<BaseResponse<T>>() {
    fun observeState(owner: LifecycleOwner, callback: ResultCallback<T>.() -> Unit) {
        val listener = ResultCallback<T>().also(callback)
        val observer = object : IStateLiveData<T>() {
            override fun onSuccess(result: T) {
                listener.success?.invoke(result)
            }

            override fun onEmpty() {
                listener.empty?.invoke()
            }

            override fun onFail(code: Int, message: String) {
                listener.fail?.invoke(code, message)
            }

            override fun onError(throwable: Throwable) {
                listener.error?.invoke(throwable)
            }

            override fun onComplete() {
                listener.complete?.invoke()
            }

        }
        super.observe(owner, observer)
    }
}