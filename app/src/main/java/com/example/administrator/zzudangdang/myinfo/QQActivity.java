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


public class QQActivity extends AppCompatActivity {

    //TODO 用户手机号和密码以后都要初始化
    private static String phone = "18838951998";
    private static String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qqbutongjj);
        final TextView editQQ=(TextView)findViewById(R.id.edit_QQ);
        findViewById(R.id.saveQQNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QQNo=editQQ.getText().toString();
                if (!TextUtils.isEmpty(QQNo)) {
                    //先销毁以前的活动
                    MyInfoActivity.instance.finish();
                    Intent intent =new Intent(getApplicationContext(),MyInfoActivity.class);
                    startActivity(intent);
                    //发送数据给网络
                    sendRequestForChange(QQNo);
                    finish();
                }
            }
        });
        findViewById(R.id.returnqq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendRequestForChange(final String QQNo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/updateUserQQ?phone=" +
                                    phone + "&password=" + password + "&qq=" + QQNo)
                            .build();
                    final Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}