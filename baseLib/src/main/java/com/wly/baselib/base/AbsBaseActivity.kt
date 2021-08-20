package com.wly.baselib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbsBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutResId() !=0){
            setContentView(layoutResId())
            initView()
        }
    }

    abstract fun layoutResId(): Int

    abstract fun initView()

}