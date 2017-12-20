package com.example.administrator.zzudangdang.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by 25534 on 2017/12/20.
 */

public class MyApplication extends Application {
    public static Context context =null;
    public static Resources resource =null;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        resource = getResources();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context mcontext) {
        MyApplication.context = mcontext;
    }

    public static Resources getResource() {
        return resource;
    }

    public static void setResource(Resources mresource) {
        MyApplication.resource = mresource;
    }
}
