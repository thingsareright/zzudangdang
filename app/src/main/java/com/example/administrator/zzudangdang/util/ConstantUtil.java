package com.example.administrator.zzudangdang.util;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 这是一些常量
 */

public class ConstantUtil {
    private static String server = "http://24981bdc.ngrok.io";
    public static String getServer() {
        return server;
    }
    public static void setServer(String server) {
        ConstantUtil.server = server;
    }
}
