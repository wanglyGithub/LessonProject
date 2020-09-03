package com.wly.lesson.mvp;


import com.wly.baselib.model.BaseModel;

/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:41
 * @Version: 1.0
 */
public class MainModel extends BaseModel implements MainContract.IMainModel {
    @Override
    public void loadData(ResultCallBack resultCallBack) {
        try {
            Thread.sleep(1200);
            resultCallBack.onParseResultSuccess("成功哦了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
