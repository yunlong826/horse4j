package com.horse.yun.tool.model;

import lombok.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 方法执行结果
 * @date 2022/6/21 15:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MethodExecuteResult {
    /**
     * 是否成功
     */
    @NonNull
    private boolean success;

    /**
     * 异常
     */
    private Throwable throwable;

    /**
     * 错误日志
     */
    private String errorMsg;
}
