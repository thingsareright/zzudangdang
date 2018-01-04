package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/3/26.
 * 商品信息
 */

public class GoodsInfo {
    private int id;
    private String name;
    private boolean isChoosed;
    private String imageUrl;
    private double price;
    private double prime_price;
    private int postion;
    private int count;  //这里要特别注意，count才是用户要购买的数量
    private String color;
    private String goodsImg;
    private int number;

    public GoodsInfo(int id, String name, double price, double prime_price,
                     String color, String goodsImg, int count,int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.prime_price = prime_price;
        this.count = count;
        this.color = color;
        this.goodsImg = goodsImg;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrime_price() {
        return prime_price;
    }

    public void setPrime_price(double prime_price) {
        this.prime_price = prime_price;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}