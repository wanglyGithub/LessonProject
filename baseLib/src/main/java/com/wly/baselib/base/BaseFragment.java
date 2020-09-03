package com.wly.baselib.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;


import com.wly.baselib.presenter.BasePresenter;
import com.wly.baselib.presenter.UILogController;


/**
 * @Description: BaseFragment 基类
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:29
 * @Version: 1.0
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    private static final String CLASS_TAG = "[UIBase] -> BaseFragment ";

    private P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        UILogController.i(CLASS_TAG, " onAttach()");


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UILogController.i(CLASS_TAG, " onCreate()");

    }

    /**
     * 子类实现Presenter,且必须继承BasePresenter
     *
     * @return P
     */
    protected abstract P createPresenter();


    @Override
    public void onStart() {
        super.onStart();
        UILogController.i(CLASS_TAG, " onStart()");

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UILogController.i(CLASS_TAG, " onActivityResult()");


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UILogController.i(CLASS_TAG, " onViewCreated()");

    }

    @Override
    public void onResume() {
        super.onResume();
        UILogController.i(CLASS_TAG, " onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();
        UILogController.i(CLASS_TAG, " onPause() : ");

    }

    @Override
    public void onStop() {
        super.onStop();
        UILogController.i(CLASS_TAG, " onStop() : ");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        UILogController.i(CLASS_TAG, " onDestroyView() : ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UILogController.i(CLASS_TAG, " onDestroy() : ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        UILogController.i(CLASS_TAG, " onDetach() : ");

    }

}
