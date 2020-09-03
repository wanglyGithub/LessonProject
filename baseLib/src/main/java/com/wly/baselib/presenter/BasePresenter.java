package com.wly.baselib.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;


import com.wly.baselib.base.IBaseView;
import com.wly.baselib.model.DefaultModel;
import com.wly.baselib.model.IBaseModel;

import java.lang.ref.SoftReference;

/**
 * @Description: BasePresenter
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 11:51
 * @Version: 1.0
 */
public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter<V> {

    private static final String TAG = "BasePresenter";
    /**
     * 当前的实体类.
     * Activity or fragment   接口类型的弱引用
     */
    private SoftReference<V> mViewRef;

    /**
     * 对应数据处理model
     */
    private M mModel;

    /**
     * 只要不是与UI相关的操作，请使用此Context。避免直接使用ActivityContext,造成页面无法销毁。
     */
    private Context mAppContext;

    /**
     * view 是否已经销毁
     */
    private boolean isViewDestroy = false;


    /**
     * 支持model层
     *
     * @return 继承baseModel
     */
    protected abstract M initModel();


    @Override
    public void onAttach(V view) {
        this.isViewDestroy = false;
        this.mViewRef = new SoftReference<>(view);
        this.mModel = getModel();
    }

    protected final M getModel() {
        if (this.mModel != null) {
            return this.mModel;
        }
        this.mModel = initModel();
        if (this.mModel == null) {
            this.mModel = (M) new DefaultModel();
        }
        return this.mModel;
    }

    /**
     * 只要不是与UI相关的操作，请使用此Context。避免直接使用Activity Context,造成页面无法销毁。
     *
     * @return Context
     */
    public Context getAppContext() {
        return mAppContext;
    }


    @Override
    public void onDetach() {
        destroyView();
        UILogController.d(TAG,"onDetach()···");
        this.mModel = null;
    }

    @Override
    public V getView() {
        if (this.mViewRef != null) {
            return this.mViewRef.get();
        }
        return null;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        UILogController.d(TAG,"onCreate");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        UILogController.d(TAG,"onPause");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        UILogController.d(TAG,"onResume");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        UILogController.d(TAG,"onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        UILogController.d(TAG,"onDestroy");
    }

    public boolean isViewDestroyed() {
        return this.isViewDestroy;
    }

    private void destroyView() {
        this.isViewDestroy = true;
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
