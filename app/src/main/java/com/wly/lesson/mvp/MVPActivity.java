package com.wly.lesson.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.wly.baselib.base.BaseActivity;
import com.wly.lesson.R;

/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/3 18:59
 * @Version: 1.0
 */
public class MVPActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    private TextView textView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        textView = findViewById(R.id.tv_info);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });

    }

    @Override
    public void onFailed() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
