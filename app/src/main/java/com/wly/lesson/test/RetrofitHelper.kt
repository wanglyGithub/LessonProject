package com.wly.lesson.test

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author: wangliyun
 * date: 2021-05-15
 * description:
 */
class RetrofitHelper {

    companion object{


        fun getRetofit():Retrofit{
            val builder = OkHttpClient.Builder()

            return Retrofit.Builder()
                    .baseUrl("")
                    .client(builder.build())
                    .build()
        }
    }
}