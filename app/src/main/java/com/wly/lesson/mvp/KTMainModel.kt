package com.wly.lesson.mvp

import com.wly.baselib.base.impl.ResultCallback
import com.wly.baselib.model.BaseModel
import com.wly.lesson.contract.TestConstract

class KTMainModel : BaseModel(),TestConstract.Model{
    override fun requestList(callback: ResultCallback<String>) {
        callback.onSuccess("测时")
    }
}