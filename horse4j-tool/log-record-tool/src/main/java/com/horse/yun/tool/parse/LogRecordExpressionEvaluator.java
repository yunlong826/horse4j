package com.horse.yun.tool.parse;

import com.google.common.collect.Maps;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:25
 */
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {
    private Map<ExpressionKey, Expression> expressionCache = Maps.newConcurrentMap();

    private final Map<AnnotatedElementKey, Method> targetMethodCache = Maps.newConcurrentMap();

    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        Object value = getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, Object.class);
        return value == null ? "" : value.toString();
    }

    public EvaluationContext createEvaluationContext(Method method, Object[] args, Class<?> targetClass,
                                                     Object result, String errorMsg, BeanFactory beanFactory) {
        Method targetMethod = getTargetMethod(targetClass, method);
        LogRecordEvaluationContext evaluationContext = new LogRecordEvaluationContext(
                null, targetMethod, args, getParameterNameDiscoverer(), result, errorMsg);
        if (beanFactory != null) {
            evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        return evaluationContext;
    }

    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(methodKey);
        if (targetMethod == null) {
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            this.targetMethodCache.put(methodKey, targetMethod);
        }
        return targetMethod;
    }
}
