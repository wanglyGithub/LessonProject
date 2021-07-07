package com.wly.baselib.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * author: wanglyGithub
 * date: 2021/7/7
 * description: baseFragment 待完善
 */
abstract class BaseFragment : Fragment() {


    abstract fun getLayoutId(): Int


    open fun initView(root: View?, savedInstanceState: Bundle?) {

    }

    open fun initData(){

    }
}