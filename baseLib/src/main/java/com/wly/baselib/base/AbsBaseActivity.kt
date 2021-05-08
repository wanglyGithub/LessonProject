package com.wly.baselib.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class AbsBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    abstract fun initView()

}