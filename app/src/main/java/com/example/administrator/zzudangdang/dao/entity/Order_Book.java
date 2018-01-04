package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/12/4 0004.
 * web端order_book表的元组映射
 **/

public class Order_Book {

    private int order_id;   //订单编号
    private int book_id;    //书籍id
    private int boss_id;    //商家id
    private int num;        //在此订单编号对应的订单，对应的书籍和对应的商家下，这本书买了多少本

    public Order_Book(int order_id, int book_id, int boss_id, int num) {
        this.order_id = order_id;
        this.book_id = book_id;
        this.boss_id = boss_id;
        this.num = num;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
