package com.wly.baselib.kotlin.contract

import com.wly.baselib.kotlin.IBasePresenter
import com.wly.baselib.kotlin.IModel
import com.wly.baselib.kotlin.IView

interface CommonContract {

    interface View:IView{

    }

    interface Presenter<V:View>:IBasePresenter<V>{

    }

    interface Model:IModel{

    }
}