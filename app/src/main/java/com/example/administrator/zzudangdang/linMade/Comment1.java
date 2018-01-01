package com.example.administrator.zzudangdang.linMade;

/**
 * Created by 沐莲心 on 2017/12/30.
 */

public class Comment1 {
    private int Bimage;
    private String Bname;
    private int Bprice;

    public Comment1(int Bimage, String Bname, int Bprice) {
        this.Bimage = Bimage;
        this.Bname = Bname;
        this.Bprice = Bprice;
    }

    public  int getBimage() {
        return Bimage;
    }
    public String getBname() {
        return Bname;
    }
    public int getBprice() {
        return Bprice;
    }
}
