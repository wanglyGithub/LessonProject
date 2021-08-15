package com.wly.baselib.mvvm.base

import androidx.lifecycle.ViewModel
import com.wly.baselib.livedata.SingleLiveData

/**
 * author: wanglyGithub
 * date: 2021/8/13
 * description:
 */
open class BaseViewModel: ViewModel() {
    var loadingEvent = SingleLiveData<Boolean>()
    val errorEvent = SingleLiveData<Throwable>()

    
}