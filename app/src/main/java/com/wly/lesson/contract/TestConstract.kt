package com.wly.lesson.contract

import com.wly.baselib.base.impl.ResultCallback

interface TestConstract {

    interface View:CommonContract.View{
        fun showData(data:String)
        fun showEmpty()
    }

    interface Presenter:CommonContract.Presenter<View>{
        fun request(map: HashMap<String,String>)
    }

    interface Model:CommonContract.Model{
        fun requestList(callback:ResultCallback<String>)
    }
}