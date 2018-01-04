package com.example.administrator.zzudangdang.dao.entity;


import java.io.Serializable;
import java.util.List;

/**
 * 这个类是用于向小建哥提供的页面注入数据
 */
public class BookToClientforSingleBook implements Serializable{
    private int bookid;
    private int bossid;
    private List<String> imageAddressList;      //商品图片地址列表
    private String book_name;                   //书的名字
    private float price;                        //书的价格
    private String writter;                     //书的作者
    private String press;                       //出版社
    private String boss_image;                  //商家头像
    private String boss_name;                   //商家名字


    public BookToClientforSingleBook() {
    }

    public BookToClientforSingleBook(int bookid, int bossid, List<String> imageAddressList, String book_name, float price, String writter, String press, String boss_image, String boss_name) {
        this.bookid = bookid;
        this.bossid = bossid;
        this.imageAddressList = imageAddressList;
        this.book_name = book_name;
        this.price = price;
        this.writter = writter;
        this.press = press;
        this.boss_image = boss_image;
        this.boss_name = boss_name;
    }

    public List<String> getImageAddressList() {
        return imageAddressList;
    }

    public void setImageAddressList(List<String> imageAddressList) {
        this.imageAddressList = imageAddressList;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getBoss_image() {
        return boss_image;
    }

    public void setBoss_image(String boss_image) {
        this.boss_image = boss_image;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getBossid() {
        return bossid;
    }

    public void setBossid(int bossid) {
        this.bossid = bossid;
    }
}
