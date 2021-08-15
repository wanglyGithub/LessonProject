package com.wly.baselib.net.status

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.wly.baselib.net.bean.BaseResponse

/**
 * author: wanglyGithub
 * date: 2021/8/13
 * description:
 */
class StateLiveData<T> : MutableLiveData<BaseResponse<T>>() {

    fun observeState(owner: LifecycleOwner) {
        val observer = object : IStateLiveData<T>() {
            override fun onSuccess(result: T) {

            }

            override fun onEmpty() {
            }

            override fun onFail(code: Int, message: String) {
            }

            override fun onError(throwable: Throwable) {
            }

            override fun onComplete() {
            }

        }
        super.observe(owner, observer)
    }
}