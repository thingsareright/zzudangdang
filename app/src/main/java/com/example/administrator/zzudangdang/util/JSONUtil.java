package com.example.administrator.zzudangdang.util;

import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.dao.entity.BookToClientforSingleBook;
import com.example.administrator.zzudangdang.dao.entity.ShopCart;
import com.example.administrator.zzudangdang.dao.entity.User;
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

    /**
     * 这个方法用于解析商品详情界面需要返回的数据
     * @param jsonData
     * @return
     */
    public static BookToClientforSingleBook parseSingleBookWithGSON(String jsonData) {
        Gson gson = new Gson();
        BookToClientforSingleBook book = gson.fromJson(jsonData, BookToClientforSingleBook.class);
        return book;
    }

    /**
     * 这个方法用于解析购物车界面需要返回的数据
     * @param jsonData
     * @return
     */
    public static ShopCart parseShopCarWithGSON(String jsonData) {
        Gson gson = new Gson();
        ShopCart shopCart = gson.fromJson(jsonData, ShopCart.class);
        return shopCart;
    }

    /**
     * 这个方法用于解析用户界面需要返回的数据
     * @param jsonData
     * @return
     */
    public static User parseUserWithGSON(String jsonData) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData, User.class);
        return user;
    }



}
