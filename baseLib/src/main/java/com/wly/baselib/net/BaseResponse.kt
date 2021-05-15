package com.wly.baselib.net

/**
 * author: wanglyGithub
 * date: 2021-05-15
 * description:
 */
class BaseResponse<T> {
    val data: T ? = null

    val code: Int = 0

    val errorCode :Int = 0

    val errorMsg:String = ""

}