@file:JvmName(name = "ExceptionHandle")
package com.wly.baselib.net.exception
import android.widget.Toast
import com.wly.baselib.BaseApplication
import com.wly.baselib.utils.UILogUtil
import org.apache.http.conn.ConnectTimeoutException
import java.net.ConnectException
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
        is SocketTimeoutException , is ConnectTimeoutException-> {
            showToast("网络链接超时，请稍后重试")
        }

        is CancellationException, is ConnectException -> {
            showToast("网络连接不可用，请检查网络")
        }

    }
}


fun showToast(text:String){
    Toast.makeText(BaseApplication.instance.context,text,Toast.LENGTH_SHORT).show()

}


enum class APICode(val code: Int, val msg: String) {
    TOKEN_EXPIRED(410, "token_expired")
}