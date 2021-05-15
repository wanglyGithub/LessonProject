package com.wly.kotlinlib.design

import android.util.Log

/**
 * author: wanglyGithub
 * date: 2021-05-15
 * description: 恶汉单例 默认方式，并且效率低，浪费资源
 */
object SingleInstance {

    const val TAG = "SingleInstance"
    fun showToast() {
        Log.i(TAG, "showToast")
    }

}