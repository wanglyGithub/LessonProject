package com.wly.baselib.mvvm.base

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * author: wanglyGithub
 * date: 2021/8/12
 * description:
 */
abstract class BaseViewModel<M : BaseModel> : ViewModel(), IBaseViewModel {
    companion object{
        const val TAG = "BaseViewModel"
    }
    protected var mModel: M? = null


    //TODO:暂时
    init {
        mModel = createRepository()
        Log.i(TAG,"BaseViewModel --> init mModel = $mModel")
    }


     abstract fun createRepository(): M?

    override fun onCreate() {
        mModel = createRepository()
        Log.i(TAG,"BaseViewModel --> onCreate mModel = $mModel")
    }

    override fun onResume() {
    }

    override fun onDestroy() {
        mModel = null
        Log.i(TAG,"BaseViewModel --> onDestroy mModel = $mModel")

    }

    override fun onCleared() {
        super.onCleared()
        mModel = null
        Log.i(TAG,"BaseViewModel --> onCleared mModel = $mModel")

    }


    open fun getParamMap(map: MutableMap<String,Any?>.()->Unit):MutableMap<String,Any?>{

        return mutableMapOf()
    }

}