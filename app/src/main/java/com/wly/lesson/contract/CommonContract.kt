package com.wly.lesson.contract

import com.wly.baselib.presenter.IBasePresenter
import com.wly.baselib.model.IModel
import com.wly.baselib.base.impl.IView

interface CommonContract {

    interface View: IView {

    }

    interface Presenter<V:View>: IBasePresenter<V> {

    }

    interface Model: IModel {

    }
}