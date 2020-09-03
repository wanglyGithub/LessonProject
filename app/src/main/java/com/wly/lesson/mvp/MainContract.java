package com.wly.lesson.mvp;


import com.wly.baselib.base.IBaseView;
import com.wly.baselib.model.IBaseModel;
import com.wly.baselib.presenter.IPresenter;

/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:42
 * @Version: 1.0
 */
public interface MainContract {

    interface IMainView extends IBaseView {
        void onSuccess(String data);
        void onFailed();
    }

    interface IMainModel extends IBaseModel {
        void loadData(ResultCallBack resultCallBack);
    }

    interface IMainPresenter extends IPresenter {
        void loadData();
    }
}
