package com.wly.baselib.base.impl

interface ResultCallback<T> {
    fun onSuccess(data: T)
    fun onFailed(errorCode: Int, errorMsg: String)
}