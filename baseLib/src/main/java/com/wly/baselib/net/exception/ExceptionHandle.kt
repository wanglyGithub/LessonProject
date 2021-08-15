@file:JvmName(name = "ExceptionHandle")

package com.wly.baselib.net.exception

import com.wly.baselib.utils.UILogUtil
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

/**
 * author: wanglyGithub
 * date: 2021/8/15
 * description: 异常统一处理
 */

const val TAG = "ExceptionHandle"

/**
 * 处理Api异常
 * @param errorCode
 * @param errorMsg
 */
internal fun handleApiException(errorCode: Int, errorMsg: String) {
    when (errorCode) {
        APICode.SIGN_OUT.code -> {
            UILogUtil.e(TAG, "退出登录操作")
        }
        APICode.TOKEN_EXPIRED.code -> {
            UILogUtil.e(TAG, "toke 失效，退出登录操作")
        }
    }
}

/**
 * 处理非API级别异常
 * @param throwable
 */
internal fun handleOtherException(throwable: Throwable) {
    when (throwable) {
        is SocketTimeoutException -> {

        }

        is CancellationException -> {

        }
    }
}


enum class APICode(val code: Int, val msg: String) {
    SIGN_OUT(1001, "sign_out"),
    TOKEN_EXPIRED(10002, "token_expired")
}