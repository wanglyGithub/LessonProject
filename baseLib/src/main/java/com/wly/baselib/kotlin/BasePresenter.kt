package com.wly.baselib.kotlin

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner

abstract class BasePresenter<V : IView, M : IModel> : IBasePresenter<V> {
    private var mModel: M? = null
    private var mView: V? = null


    val isViewDestroy: Boolean
        get() = mView == null

    open fun initModel(): M? = null

    override fun onAttachView(view: V) {
        this.mView = view
        this.mModel = initModel()
        if (mView is LifecycleOwner) {
            (mView as? LifecycleOwner?)?.lifecycle?.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }
    }

    override fun onDetachView() {
        this.mModel = null
        this.mView = null
    }

    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
        onDetachView()
    }
}