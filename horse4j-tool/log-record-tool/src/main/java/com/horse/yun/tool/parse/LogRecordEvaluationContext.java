package com.horse.yun.tool.parse;

import com.horse.yun.tool.context.LogRecordContext;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:26
 */
public class LogRecordEvaluationContext extends MethodBasedEvaluationContext {
    public LogRecordEvaluationContext(Object rootObject, Method method, Object[] arguments,
                                      ParameterNameDiscoverer parameterNameDiscoverer,Object ret, String errorMsg) {
        super(rootObject, method, arguments, parameterNameDiscoverer);

        Map<String, Object> variables = LogRecordContext.getVariables();
        if (variables != null && variables.size() > 0) {
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                setVariable(entry.getKey(), entry.getValue());
            }
        }

        setVariable("_ret", ret);
        setVariable("_errorMsg", errorMsg);
    }
}
