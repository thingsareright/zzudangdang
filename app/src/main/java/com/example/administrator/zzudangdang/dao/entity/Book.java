package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/12/4 0004.
 * 数据库book的映射java文件，超完全映射，中间加了个price
 */

public class Book {
    private int id; //书籍的唯一标识（主键）
    private String book_name;   //书名
    private String writter; //作者
    private String press;   //出版社
    private int version;    //版本号
    private String ISBN;    //ISBN号
    private int format;     //开本
    private int paper;      //纸张（0表示胶版纸）
    private int book_package;   //包装方式（0表示平装，1表示精装，必须向下兼容）
    private int book_kind;  //书籍分类（具体的类型还没有定义）
    private float book_price;   //为了方便，这里与数据库表并不一样
    private String book_picture;    //同上，这里存储第一张搜索到的图片


    public Book(int id, String book_name, String writter, String press, int version, String ISBN, int format, int paper, int book_package, int book_kind, float book_price, String book_picture) {
        this.id = id;
        this.book_name = book_name;
        this.writter = writter;
        this.press = press;
        this.version = version;
        this.ISBN = ISBN;
        this.format = format;
        this.paper = paper;
        this.book_package = book_package;
        this.book_kind = book_kind;
        this.book_price = book_price;
        this.book_picture = book_picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public int getBook_package() {
        return book_package;
    }

    public void setBook_package(int book_package) {
        this.book_package = book_package;
    }

    public int getBook_kind() {
        return book_kind;
    }

    public void setBook_kind(int book_kind) {
        this.book_kind = book_kind;
    }

    public float getBook_price() {
        return book_price;
    }

    public void setBook_price(float book_price) {
        this.book_price = book_price;
    }

    public String getBook_picture() {
        return book_picture;
    }

    public void setBook_picture(String book_picture) {
        this.book_picture = book_picture;
    }
}
