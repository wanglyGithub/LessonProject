package com.wly.lesson.mvp;

/**
 * @Description: java类作用描述
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 17:22
 * @Version: 1.0
 */
public interface ResultCallBack {

    void onParseResultSuccess(String data);

    void onParseResultFailed();
}
