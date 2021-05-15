package com.wly.lesson.dsl

import android.text.Editable
import android.text.TextWatcher

/**
 * author: wanglyGitHub
 * date: 2021-05-15
 * description:DSL 方式 简化 editText 监听
 */
private typealias afterTextChanged = (p0: Editable?) -> Unit

private typealias beforeTextChanged = (p0: CharSequence?, p1: Int, p2: Int, p3: Int) -> Unit
private typealias onTextChanged = (p0: CharSequence?, p1: Int, p2: Int, p3: Int) -> Unit

class CustomTextWatcherDsl : TextWatcher {

    private var afterTextChanged: afterTextChanged? = null
    private var beforeTextChanged: beforeTextChanged? = null
    private var onTextChanged: onTextChanged? = null

    override fun afterTextChanged(p0: Editable?) {

        return afterTextChanged?.invoke(p0) ?: Unit
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return beforeTextChanged?.invoke(p0, p1, p2, p3) ?: Unit
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return onTextChanged?.invoke(p0, p1, p2, p3) ?: Unit
    }


    fun afterTextChanged(afterTextChanged: afterTextChanged) {
        this.afterTextChanged  = afterTextChanged

    }

    fun beforeTextChanged(beforeTextChanged: beforeTextChanged) {
        this.beforeTextChanged = beforeTextChanged

    }

    fun onTextChanged(onTextChanged: onTextChanged) {
        this.onTextChanged = onTextChanged

    }

}

fun customTextWatch(watcher: CustomTextWatcherDsl.()->Unit) = CustomTextWatcherDsl().also(watcher)
