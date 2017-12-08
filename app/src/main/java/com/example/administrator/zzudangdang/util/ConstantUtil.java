package com.example.administrator.zzudangdang.util;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 这是一些常量
 */

public class ConstantUtil {
    private static String server = "http://10.108.218.84:5002";
    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        ConstantUtil.server = server;
    }
}
