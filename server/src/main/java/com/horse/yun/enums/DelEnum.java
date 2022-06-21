package com.horse.yun.enums;

public enum DelEnum {
    /**
     * 正常状态
     */
    NORMAL("0"),

    /**
     * 删除状态
     */
    DELETE("1");

    private final String statusCode;

    DelEnum(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return this.statusCode;
    }

    public Integer getIntCode() {
        return Integer.parseInt(this.statusCode);
    }

    @Override
    public String toString() {
        return statusCode;
    }
}
