package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.ConstantUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WechatActivity extends AppCompatActivity {

    //TODO 用户手机号和密码以后都要初始化
    private static String phone = "18838951998";
    private static String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechatbuttongjj);

        final TextView editWechat = (TextView)findViewById(R.id.editwechat);
        findViewById(R.id.saveWechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wechatno = editWechat.getText().toString();
                //先销毁以前的活动
                MyInfoActivity.instance.finish();
                Intent intent =new Intent(getApplicationContext(),MyInfoActivity.class);
                //发送数据给网络
                sendRequestForChange(wechatno);
                startActivity(intent);
                finish();
                }
            });
        }




    private void sendRequestForChange(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/updateUserWeChat?phone=" +
                                    phone + "&password=" + password + "&wechat=" + text)
                            .build();
                    final Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
