package com.horse.yun.tool.parse;

import com.horse.yun.tool.annotation.LogRecord;
import com.horse.yun.tool.model.LogRecordOps;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 日志记录操作解析
 * @date 2022/6/21 15:21
 */
public class LogRecordOperationSource {
    public Collection<LogRecordOps> computeLogRecordOperations(Method method, Class<?> targetClass) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        // 得到实现类方法，而不是接口方法
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // 得到桥接方法
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        return parseLogRecordAnnotations(specificMethod);
    }
    private Collection<LogRecordOps> parseLogRecordAnnotations(AnnotatedElement ae) {
        Collection<LogRecord> logRecordAnnotations = AnnotatedElementUtils.getAllMergedAnnotations(ae, LogRecord.class);
        Collection<LogRecordOps> ret = null;
        if (!logRecordAnnotations.isEmpty()) {
            ret = new ArrayList(1);
            for (LogRecord logRecord : logRecordAnnotations) {
                ret.add(parseLogRecordAnnotation(ae, logRecord));
            }
        }

        return ret;
    }
    private LogRecordOps parseLogRecordAnnotation(AnnotatedElement ae, LogRecord logRecord) {
        LogRecordOps recordOps = LogRecordOps.builder()
                .successLogTemplate(logRecord.success())
                .failLogTemplate(logRecord.fail())
                .bizKey(logRecord.prefix().concat(logRecord.bizNo()))
                .bizNo(logRecord.bizNo())
                .operatorId(logRecord.operator())
                .category(StringUtils.isEmpty(logRecord.category()) ? logRecord.prefix() : logRecord.category())
                .detail(logRecord.detail())
                .condition(logRecord.condition())
                .build();

        validateLogRecordOperation(ae, recordOps);
        return recordOps;
    }

    private void validateLogRecordOperation(AnnotatedElement ae, LogRecordOps recordOps) {
        if (!StringUtils.hasText(recordOps.getSuccessLogTemplate()) && !StringUtils.hasText(recordOps.getFailLogTemplate())) {
            throw new IllegalStateException("Invalid logRecord annotation configuration on '" +
                    ae.toString() + "'. 'one of successTemplate and failLogTemplate' attribute must be set.");
        }
    }
}
