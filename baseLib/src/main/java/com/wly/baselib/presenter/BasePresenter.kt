package com.wly.baselib.presenter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.wly.baselib.model.IModel
import com.wly.baselib.base.impl.IView
import com.wly.baselib.utils.UILogUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BasePresenter<V : IView, M : IModel> : IBasePresenter<V> {
    protected var mModel: M? = null
    protected var mView: V? = null

    protected val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

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
        UILogUtil.d(message = "BasePresenter onDestroy()")
        owner.lifecycle.removeObserver(this)
        presenterScope.cancel()
        onDetachView()
    }
}