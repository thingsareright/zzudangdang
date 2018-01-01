package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.UserUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EmailActivity extends AppCompatActivity {

    //TODO 用户手机号和密码以后都要初始化
    private static String phone = UserUtil.getOnlyUser().getPhone();
    private static String password = UserUtil.getOnlyUser().getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailbuttongjj);
        final TextView editemail=(TextView) findViewById(R.id.editEmail);



        findViewById(R.id.saveEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editemail.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    //先销毁以前的活动
                    MyInfoActivity.instance.finish();
                    Intent intent =new Intent(getApplicationContext(),MyInfoActivity.class);
                    startActivity(intent);
                    //发送数据给网络
                    sendRequestForChange(email);
                    finish();
                }

            }
        });
        findViewById(R.id.returnemail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendRequestForChange(final String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient  client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/updateUserEmail?phone=" +
                                    phone + "&password=" + password + "&email=" + email)
                            .build();
                    final Response response = client.newCall(request).execute();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
