package com.wly.lesson.mvp

import com.wly.baselib.base.impl.ResultCallback
import com.wly.baselib.presenter.BasePresenter
import com.wly.lesson.contract.TestConstract

class KTMainPresenter : BasePresenter<TestConstract.View, TestConstract.Model>(), TestConstract.Presenter {

    override fun initModel(): TestConstract.Model? = KTMainModel()

    override fun request(map: HashMap<String, String>) {

        mModel?.requestList(object : ResultCallback<String> {
            override fun onSuccess(data: String) {
                mView?.showData(data)
            }

            override fun onFailed(errorCode: Int, errorMsg: String) {
                mView?.showEmpty()
            }
        })

    }
}