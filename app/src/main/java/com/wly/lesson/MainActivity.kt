package com.wly.lesson

import com.wly.baselib.base.KTBaseActivity
import com.wly.baselib.utils.UILogUtil
import com.wly.lesson.contract.TestConstract
import com.wly.lesson.mvp.KTMainPresenter

class MainActivity : KTBaseActivity<KTMainPresenter, TestConstract.View>(), TestConstract.View {
    override fun initPresenter(): KTMainPresenter = KTMainPresenter()

    override fun layoutResId(): Int? = R.layout.activity_main

    override fun initView() {
        super.initView()
        mPresenter?.request(map = HashMap())
    }

    override fun showData(data: String) {
        UILogUtil.d(message = "result: $data")
    }

    override fun showEmpty() {
        UILogUtil.d(message = "showEmpty: ")
    }
}
