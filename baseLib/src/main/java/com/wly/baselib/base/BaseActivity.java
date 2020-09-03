package com.wly.baselib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wly.baselib.presenter.BasePresenter;
import com.wly.baselib.presenter.DefaultPresenter;
import com.wly.baselib.presenter.UILogController;


/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:23
 * @Version: 1.0
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {


    protected final String TAG = this.getClass().getSimpleName();

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        this.mPresenter = createPresenter();
        this.getPresenter().onAttach(this);
        getLifecycle().addObserver(getPresenter());
        initView(savedInstanceState);
        initData();
    }

    protected abstract int getLayoutResId();

    /**
     * 子类实现Presenter,且必须继承BasePresenter
     *
     * @return P
     */
    protected  P createPresenter(){
        return null;
    }


    /**
     * 初始化相关view
     *
     * @param savedInstanceState bundle 数据
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);


    /**
     * 初始化data
     */
    protected abstract void initData();


    @NonNull
    protected final P getPresenter() {
        if (this.mPresenter != null) {
            return this.mPresenter;
        }
        this.mPresenter = createPresenter();
        if (this.mPresenter == null) {
            UILogController.e(TAG, " --- used DefaultPresenter ---");
            //noinspection unchecked
            this.mPresenter = (P) new DefaultPresenter();
        }
        return this.mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDetach();
    }
}
