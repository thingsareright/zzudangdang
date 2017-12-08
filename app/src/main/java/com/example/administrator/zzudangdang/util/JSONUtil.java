package com.example.administrator.zzudangdang.util;

import com.example.administrator.zzudangdang.dao.entity.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 这个类用来数据流转换，将网络传过来的数据进行json格式转换
 */

public class JSONUtil {

    /**
     * 这个方法用于解析商品列表界面需要返回的数据
     * @param jsonData
     * @return
     */
    public static List<Book> parseBookWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<Book> bookList = gson.fromJson(jsonData, new TypeToken<List<Book>>(){}.getType());
        return bookList;
    }
}
