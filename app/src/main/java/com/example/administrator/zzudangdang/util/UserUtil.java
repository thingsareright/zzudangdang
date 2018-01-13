package com.example.administrator.zzudangdang.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.example.administrator.zzudangdang.activity.Home_Activity;
import com.example.administrator.zzudangdang.activity.Login_Activity;
import com.example.administrator.zzudangdang.dao.entity.User;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class UserUtil {

    private static User user =null;

    public static User getOnlyUser() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        User user = new User();

        if (!sp.contains("phone")){
            Intent intent = new Intent(MyApplication.getContext(), Login_Activity.class);
            MyApplication.getContext().startActivity(intent);
        }
        user.setPhone(sp.getString("phone","18838951998"));
        user.setPassword(sp.getString("password","123"));
        user.setHead(sp.getString("headuri","yinlin.jpg"));
        return user;
    }

    public static void setOnlyUser(final User user1)  {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains("phone")) {
            editor.remove("phone");
            editor.commit();
        }
        if (sp.contains("password")){
            editor.remove("password");
            editor.commit();
        }
        if (sp.contains("headuri")) {
            editor.remove("headuri");
            editor.commit();
        }
        editor.putString("phone", user1.getPhone());
        editor.putString("password", user1.getPassword());
        editor.putString("headuri", user1.getHead());
        editor.commit();
        editor.apply();
    }


}
