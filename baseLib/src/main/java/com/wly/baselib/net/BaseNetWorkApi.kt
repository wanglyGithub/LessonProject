package com.wly.baselib.net

import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author: wanglyGithub
 * date: 2021/8/13
 * description: 网络层基类
 */
abstract class BaseNetWorkApi {

    fun <T> getApi(serviceApi: Class<T>, baseUrl: String): T {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
        return setRetrofitBuilder(retrofitBuilder).build().create(serviceApi)
    }

    /**
     * @param builder
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，Protocol
     */
    abstract fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder

    /**
     * @param builder
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    abstract fun getOkHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder

    private val okHttpClient: OkHttpClient
        get() {
            var builder = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
            builder = getOkHttpClientBuilder(builder)
            return builder.build()

        }

}