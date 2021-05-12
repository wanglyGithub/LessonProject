package com.wly.baselib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbsBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutResId()?.let {
            setContentView(it)
            initView()
        }
    }

    abstract fun layoutResId(): Int?

    abstract fun initView()

}