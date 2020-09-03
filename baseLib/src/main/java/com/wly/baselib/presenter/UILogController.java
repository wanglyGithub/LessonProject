package com.wly.baselib.presenter;

import android.util.Log;

import com.wly.baselib.BuildConfig;

/**
 * @Description: UIBase日志打印工具类
 * @Author: wangliyun
 * @CreateDate: 2020/9/2 14:15
 * @Version: 1.0
 */
public class UILogController {



    public static void d(String tag,String message){
        if (BuildConfig.enableLog){
            Log.d(tag,message);
        }
    }

    public static void i(String tag,String message){
        if (BuildConfig.enableLog){
            Log.i(tag,message);
        }
    }

    public static void e(String tag,String message){
        if (BuildConfig.enableLog){
            Log.e(tag,message);
        }
    }
}
