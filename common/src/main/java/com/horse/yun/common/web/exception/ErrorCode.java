package com.horse.yun.common.web.exception;

public enum ErrorCode {
    UNKNOWN_ERROR {
        @Override
        public String getCode() {
            return "1";
        }

        @Override
        public String getMessage() {
            return "未知错误";
        }
    },

    VALIDATION_ERROR {
        @Override
        public String getCode() {
            return "2";
        }

        @Override
        public String getMessage() {
            return "参数错误";
        }
    },

    SERVICE_ERROR {
        @Override
        public String getCode() {
            return "3";
        }

        @Override
        public String getMessage() {
            return "服务异常";
        }
    };

    public abstract String getCode();

    public abstract String getMessage();
}
