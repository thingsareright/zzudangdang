package com.example.administrator.zzudangdang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.zzudangdang.gaojiujiu.BookList_Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 测试用活动跳转，下面的两行等待删除
        Intent intent = new Intent(this, BookList_Activity.class);
        startActivity(intent);
    }
}
