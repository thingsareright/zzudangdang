package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/12/4 0004.
 * 书籍和商家的关联表
 */

public class Book_Boss {
    private int book_id;    //书籍id
    private int boss_id;    //商家id
    private float price;    //这本书在这个商家里的售价
    private int sell_number;    //这本书在这个商家被卖了多少本
    private String text;        //商家对这本书的描述

    public Book_Boss(int book_id, int boss_id, float price, int sell_number, String text) {
        this.book_id = book_id;
        this.boss_id = boss_id;
        this.price = price;
        this.sell_number = sell_number;
        this.text = text;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSell_number() {
        return sell_number;
    }

    public void setSell_number(int sell_number) {
        this.sell_number = sell_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
