package com.wly.baselib.mvvm.base

import android.util.Log
import com.wly.baselib.net.bean.*
import com.wly.baselib.net.exception.handleApiException
import com.wly.baselib.net.exception.handleOtherException
import com.wly.baselib.net.status.StateLiveData

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
            if (data.data == null || data.data is List<*> && (data.data as List<*>).size == 0) {
                EmptyResponse()
            } else {
                SuccessResponse(data.data!!)
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


    // 一期

    /**
     * 废弃
     */
    suspend fun <T : Any> executeReq(
        block: suspend () -> BaseResponse<T>,
        stateLiveData: StateLiveData<T>
    ) {
        var baseRes = BaseResponse<T>()
        try {//开始请求
            val invoke = block.invoke()
            // 得到结果赋值操作

            baseRes = invoke

            if (baseRes.errorCode == 0) {
                if (baseRes.data == null || baseRes.data is List<*> && (baseRes.data as List<*>).size == 0) {
                    Log.i(TAG, "DataState.STATE_EMPTY")

                } else {
                    Log.i(TAG, "DataState.STATE_SUCCESS")
                }

            } else {
                Log.w(TAG, "DataState.STATE_FAILED")

            }
        } catch (e: Exception) {
        } finally {
            Log.i(TAG, "DataState $baseRes")
            stateLiveData.postValue(baseRes)
        }
    }
}

