package com.wly.baselib.mvvm.rep

import com.wly.baselib.net.bean.*
import com.wly.baselib.net.exception.handleApiException
import com.wly.baselib.net.exception.handleOtherException

/**
 * author: wanglyGithub
 * date: 2021/8/13
 * description: 仓库基类 管理remote/local
 */
open class BaseRepository {

    companion object {
        private const val TAG = "BaseRepository"
    }

    suspend fun <T> executeReq(block: suspend () -> BaseResponse<T>): BaseResponse<T> {
        runCatching {
            block.invoke()
        }.onSuccess { data: BaseResponse<T> ->
            return doHttpSuccess(data)
        }.onFailure { exception: Throwable ->
            return doHttpError(exception)
        }
        return EmptyResponse()

    }

    private fun <T> doHttpSuccess(data: BaseResponse<T>): BaseResponse<T> {
        return if (data.isSuccess) {
            if (data.data == null || data.data is MutableList<*> && (data.data as MutableList<*>).size == 0) {
                EmptyResponse()
            } else {
                SuccessResponse(data.data)
            }
        } else {
            //执行业务错误码
            handleApiException(data.errorCode, data.errorMsg)
            FailResponse(data.errorCode, data.errorMsg)
        }

    }


    /**
     * 非后台返回的错误
     * @param throwable
     */
    private fun <T> doHttpError(throwable: Throwable): ErrorResponse<T> {
        //处理异常
        handleOtherException(throwable)
        return ErrorResponse(throwable)
    }
}

