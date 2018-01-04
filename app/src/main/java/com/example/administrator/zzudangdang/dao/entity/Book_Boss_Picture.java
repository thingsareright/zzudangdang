package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/12/4 0004.
 * 这个类用来映射服务器上的book_boss_picture表
 */

public class Book_Boss_Picture {

    private int book_id;    //书籍标识
    private int boss_id;    //商家标识
    private String picture_url; //图片路径

    public Book_Boss_Picture(int book_id, int boss_id, String picture_url) {
        this.book_id = book_id;
        this.boss_id = boss_id;
        this.picture_url = picture_url;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(int boss_id) {
        this.boss_id = boss_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
