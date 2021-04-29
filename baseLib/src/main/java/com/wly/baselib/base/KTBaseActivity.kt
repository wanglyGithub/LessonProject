package com.wly.baselib.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wly.baselib.kotlin.IBasePresenter
import com.wly.baselib.kotlin.IView

abstract class KTBaseActivity<P : IBasePresenter<V>, V : IView> : AppCompatActivity(), IView {

    protected var mPresenter: P? = null

    protected abstract fun initPresenter(): P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    fun initView() {
        mPresenter = initPresenter()
        mPresenter?.onAttachView(this as V)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetachView()
        this.mPresenter = null
    }

}