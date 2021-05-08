package com.wly.easyeventbus

import java.lang.reflect.Method

/**
 * author: wangliyun
 * date: 2020/5/26
 * description: 注册类中的注册方法信息
 */
class SubscriberMethod(// 注册方法
        var method: Method?, // 线程类型
        var threadModel: ThreadModel?, var eventType: Class<*>?)
