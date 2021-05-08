package com.wly.easyeventbus.annotation

import com.wly.easyeventbus.ThreadModel
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * author: wangliyun
 * date: 2020/5/26
 * description:
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
annotation class Subscriber(val threadMode: ThreadModel = ThreadModel.POSTING)
