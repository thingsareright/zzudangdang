package com.example.administrator.zzudangdang.util;

import com.example.administrator.zzudangdang.dao.entity.User;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class UserUtil {

    private static User user =null;

    public static User getOnlyUser() {
        return user;
    }

    public static void setOnlyUser(User user1) {
        user = user1;
    }
}
