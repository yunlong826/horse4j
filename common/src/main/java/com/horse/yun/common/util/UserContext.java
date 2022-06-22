package com.horse.yun.common.util;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:15
 */
public class UserContext {
    private static String username;

    public static void setUserName(String username) {
        UserContext.username = username;
    }

    public static String getUserName() {
        return username;
    }
}
