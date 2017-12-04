package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/12/4 0004.
 * order表的映射
 */

public class Order {
    private int id;                     //订单编号
    private int user_id;                //下单的用户的编号
    private String address;             //收货地址
    private int need_invoice;           //是否需要发票，是的话为1，否的话为0

    public Order(int id, int user_id, String address, int need_invoice) {
        this.id = id;
        this.user_id = user_id;
        this.address = address;
        this.need_invoice = need_invoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNeed_invoice() {
        return need_invoice;
    }

    public void setNeed_invoice(int need_invoice) {
        this.need_invoice = need_invoice;
    }
}
