package com.horse.yun.tool.annotation;

import com.horse.yun.tool.enums.LogRecordTypeEnum;

import java.lang.annotation.*;

/**
 *
 * @author long_yun
 * @date 2022/6/21 15:14
 * @describe 日志记录详解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {
    /**
     * 业务前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * 操作日志文本模版
     *
     * @return
     */
    String success();

    /**
     * 操作日志失败的文本
     *
     * @return
     */
    String fail() default "";

    /**
     * 操作人
     *
     * @return
     */
    String operator() default "";

    /**
     * 业务码
     *
     * @return
     */
    String bizNo();

    /**
     * 日志详情
     *
     * @return
     */
    String detail() default "";

    /**
     * 日志种类
     *
     * @return
     */
    String category();

    /**
     * 记录类型
     *
     * @return
     */
    LogRecordTypeEnum recordType() default LogRecordTypeEnum.COMPLETE;

    /**
     * 记录日志条件
     *
     * @return
     */
    String condition() default "";
}
