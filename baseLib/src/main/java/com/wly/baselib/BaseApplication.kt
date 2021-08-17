package com.wly.baselib

import android.app.Application
import android.content.Context

/**
 * author: wanglyGithub
 * date: 2021/8/17
 * description:
 */
class BaseApplication:Application() {
    var context: Context =this
    override fun onCreate() {
        super.onCreate()
        this.context = this
    }


    companion object{
        var instance = BaseApplication()
    }
}