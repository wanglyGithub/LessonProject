package com.wly.easyeventbus.annotation;

import com.wly.easyeventbus.ThreadModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: wangliyun
 * date: 2020/5/26
 * description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscriber {

    ThreadModel threadMode() default ThreadModel.POSTING;
}
