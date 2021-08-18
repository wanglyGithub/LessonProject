package com.wly.baselib.net

import com.wly.baselib.utils.UILogUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author: wanglyGithub
 * date: 2021/8/18
 * description: Application 进行全局配置初始化
 */
class NewWorkApiInit private constructor(){

    fun config(okBuilder: OkHttpClient.Builder.() -> Unit,reBuilder:Retrofit.Builder.() ->Unit){
        val okHttpClientBuilder = OkHttpClient.Builder().also(okBuilder)
        okHttpClient = okHttpClientBuilder.build()
        UILogUtil.i(TAG,"查看配置是否一致 ${okHttpClient.connectTimeoutMillis}")
        val retrofitBuilder = Retrofit.Builder().also(reBuilder)
        retrofitBuilder.client(okHttpClient)

        retrofit = retrofitBuilder.build()

    }
    private lateinit var okHttpClient:OkHttpClient
    private lateinit var retrofit: Retrofit

    companion object {
        const val TAG = "NewWorkApiInit"

        val instance: NewWorkApiInit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NewWorkApiInit()
        }
    }
    fun getRetrofit() = retrofit
}