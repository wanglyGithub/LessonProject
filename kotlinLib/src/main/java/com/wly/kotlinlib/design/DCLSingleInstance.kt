package com.wly.kotlinlib.design

import android.util.Log

/**
 * author: wanglyGithub
 * date: 2021-05-15
 * description: DCL 双重检索单例
 */

class DCLSingleInstance private constructor(){


    /***
     * companion关键字
     * 与Java不同Kotlin的类中不允许你声明静态成员或方法。相反，你必须向类中添加companion对象来包装这些静态引用.
     */
    companion object{
        const val TAG = "DCLSingleInstance"

        /***
         * by关键字
         * 是kotlin语法糖之一，通过这个关键字实现了java中的委托模式，节省了大量的样板代码
         */
        val instance: DCLSingleInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            DCLSingleInstance()
        }
    }


    fun showToast(){
        Log.i(TAG,"showToast")
    }

    // 使用方式
//    DCLSingleInstance.instance.showToast()

}