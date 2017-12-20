package com.example.administrator.zzudangdang.linMade;

/**
 * Created by 沐莲心 on 2017/12/19.
 */

public class Comment_lj {
    private int Uavatar;
    private String Uname;
    private String score;
    private String Ucomment;
    private String time;

    public Comment_lj(int Uavatar, String Uname, String score, String Ucomment, String time) {
        this.Uavatar = Uavatar;
        this.Uname = Uname;
        this.score = score;
        this.Ucomment = Ucomment;
        this.time = time;
    }

    public int getUavatar() {
        return Uavatar;
    }
    public String getUname() {
        return Uname;
    }
    public String getScore() {
        return score;
    }
    public String getUcomment() {
        return Ucomment;
    }
    public String getTime() {
        return time;
    }
}
