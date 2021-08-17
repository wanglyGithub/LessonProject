package com.wly.baselib.net.bean

import java.io.Serializable

/**
 * author: wanglyGithub
 * date: 2021-05-15
 * description:
 */
open class BaseResponse<T>(
    val data: T? = null,
    open val errorCode: Int = 0, // 200||0
    open val errorMsg: String = "",
    val error: Throwable? = null
) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0
}


data class SuccessResponse<T>(val response: T) : BaseResponse<T>(data = response)
data class FailResponse<T>(override val errorCode: Int, override val errorMsg: String) :
    BaseResponse<T>(errorCode = errorCode, errorMsg = errorMsg)

//非后台返回错误，捕获到的异常
data class ErrorResponse<T>(val throwable: Throwable) : BaseResponse<T>(error = throwable)
class EmptyResponse<T> : BaseResponse<T>()

