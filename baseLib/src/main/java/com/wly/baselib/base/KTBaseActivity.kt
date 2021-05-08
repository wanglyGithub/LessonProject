package com.wly.baselib.base

import com.wly.baselib.base.impl.IView
import com.wly.baselib.presenter.IBasePresenter

abstract class KTBaseActivity<P : IBasePresenter<V>, V : IView> : AbsBaseActivity(), IView {

    var mPresenter: P? = null

    protected abstract fun initPresenter(): P


    override fun initView() {
        mPresenter = initPresenter()
        mPresenter?.onAttachView(this as V)
    }

    fun getPresenter(): P? {
        return mPresenter
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetachView()
        this.mPresenter = null
    }

}