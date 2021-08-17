package com.wly.baselib.net.dsl

/**
 * author: wanglyGithub
 * date: 2021/8/16
 * description:DSL 简化回调,亦可采用抽象方法形式Java
 */
class ResultCallback<T> {
    var success: ((T) -> Unit)? = null
    var empty: (() -> Unit)? = null
    var fail: ((Int?, String?) -> Unit)? = null
    var error: ((Throwable?) -> Unit)? = null
    var complete: (() -> Unit)? = null

    fun onSuccess(invoke: (T) -> Unit) {
        this.success = invoke
    }

    fun onEmpty(invoke: () -> Unit) {
        this.empty = invoke
    }

    fun onFail(invoke: (Int?, String?) -> Unit) {
        this.fail = invoke
    }

    fun onError(invoke: (Throwable?) -> Unit) {
        this.error = invoke
    }

    fun onComplete(invoke: () -> Unit) {
        this.complete = invoke
    }

}