package com.wly.lesson.mvp;


import com.wly.baselib.presenter.BasePresenter;

/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:41
 * @Version: 1.0
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView,MainContract.IMainModel>
        implements MainContract.IMainPresenter {
    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    public void loadData() {
        getModel().loadData(new ResultCallBack() {
            @Override
            public void onParseResultSuccess(String data) {
                if (isViewDestroyed()) {
                    return;
                }

                getView().onSuccess(data);
            }

            @Override
            public void onParseResultFailed() {
                getView().onFailed();
            }
        });
    }

}
