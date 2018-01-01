package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.UserUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//未涉及到保存这个按钮，直接对radiobutton进行了监听，但是那个sexbutton.xml保存按钮未删除

public class SexActivity extends AppCompatActivity {

    //TODO 用户手机号和密码以后都要初始化
    private static String phone = UserUtil.getOnlyUser().getPhone();
    private static String password = UserUtil.getOnlyUser().getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexbuttongjj);
        RadioButton radioButton1=(RadioButton)findViewById(R.id.radiomale) ;
        RadioButton radioButton2=(RadioButton)findViewById(R.id.radiofemale);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //先销毁以前的活动
                MyInfoActivity.instance.finish();
                Intent intent =new Intent(getApplicationContext(),MyInfoActivity.class);
                startActivity(intent);
                //发送数据给网络
                sendRequestForChange(1);
                finish();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先销毁以前的活动
                MyInfoActivity.instance.finish();
                Intent intent =new Intent(getApplicationContext(),MyInfoActivity.class);
                startActivity(intent);
                //发送数据给网络
                sendRequestForChange(0);
                finish();
            }
        });
        findViewById(R.id.returnsex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendRequestForChange(final int text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/updateUserSex?phone=" +
                                    phone + "&password=" + password + "&sex=" + text)
                            .build();
                    final Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
