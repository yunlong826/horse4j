package com.horse.yun.common.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{
    private String detail;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, String detail) {
        super(message);
        this.detail = detail;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.detail = cause.getMessage();
    }

    public ServiceException(String message, String detail, Throwable cause) {
        super(message, cause);
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "message='" + getMessage() + "'," +
                "detail='" + getDetail() + "'" +
                '}';
    }
}
