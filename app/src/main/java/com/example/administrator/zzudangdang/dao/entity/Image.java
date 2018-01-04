package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by dell on 2017/12/29.
 * 用户头像上传实体类
 */

public class Image {

    private String phone; //映射用户id

    private String base64;  //图片的base64编码

    public Image() {
    }

    public Image(String phone, String base64) {
        this.phone = phone;
        this.base64 = base64;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
