package com.example.administrator.zzudangdang.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by 25534 on 2017/12/20.
 */

public class MyApplication extends Application {
    public static Context context =null;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }
}
