package com.wly.baselib.net.status

import androidx.lifecycle.Observer
import com.wly.baselib.net.bean.*

/**
 * author: wanglyGithub
 * date: 2021/8/15
 * description:
 */
abstract class IStateLiveData<T> :Observer<BaseResponse<T>>{

    override fun onChanged(responese: BaseResponse<T>?) {
        when(responese){
            is SuccessResponse<T> -> onSuccess(result = responese.response)
            is FailResponse<T> -> onFail(responese.errorCode,responese.errorMsg)
            is EmptyResponse<T> -> onEmpty()
            is ErrorResponse<T> -> onError(throwable = responese.throwable)
        }
        onComplete()
    }


    abstract fun onSuccess(result: T)

    abstract fun onEmpty()
    abstract fun onFail(code: Int, message: String)

    abstract fun onError(throwable: Throwable)

    //代码执行完成，没有实际意义
    abstract fun onComplete()
}