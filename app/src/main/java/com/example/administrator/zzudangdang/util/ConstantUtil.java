package com.example.administrator.zzudangdang.util;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 这是一些常量
 */

public class ConstantUtil {
    private static String server = "http://120.78.178.186:5003";
    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        ConstantUtil.server = server;
    }
}
