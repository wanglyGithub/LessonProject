package com.wly.lesson

import android.app.Application
import android.util.Log
import com.google.gson.GsonBuilder
import com.wly.baselib.net.NewWorkApiInit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * date: 2021/8/18
 * description:
 */
class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewWorkApiInit.instance.config(okBuilder = {
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("DemoApplication", "log: $message")
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)).build()
        }, reBuilder = {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl("https://www.wanandroid.com")
        })
    }
}