package com.wly.lesson.test.api

import com.wly.baselib.net.BaseNetWorkApi
import com.wly.baselib.net.NewWorkApiInit
import com.wly.baselib.utils.UILogUtil

/**
 * date: 2021/8/13
 * description: 构建各个业务线自己的相关网络配置
 */

class NetWorkApi: BaseNetWorkApi() {
    companion object{
        const val TAG = "NetWorkApi"
        val INSTANCE: NetWorkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetWorkApi()
        }
    }


    fun <T> getApi(serviceApi: Class<T>): T {

        UILogUtil.i("wangly","Retrofit instance = ${NewWorkApiInit.instance.getRetrofit()}")

        return NewWorkApiInit.instance.getRetrofit().create(serviceApi)

    }

}