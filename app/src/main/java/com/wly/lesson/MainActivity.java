package com.wly.lesson;

import android.content.Context;
import android.os.Bundle;

import com.wly.baselib.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
