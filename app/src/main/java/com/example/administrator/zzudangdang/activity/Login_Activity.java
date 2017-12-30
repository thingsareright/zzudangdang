package com.example.administrator.zzudangdang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.adapter.BookAdapterForResult;
import com.example.administrator.zzudangdang.adapter.BookAdapterForThink;
import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.dao.entity.User;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.MyApplication;
import com.example.administrator.zzudangdang.util.UserUtil;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login_Activity extends AppCompatActivity {

    private Button login;
    private EditText username;
    private EditText password;
    private TextView forget;
    private TextView register;

    private String name;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.login);

        login = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        forget = (TextView) findViewById(R.id.forget_psw);
        register = (TextView) findViewById(R.id.register_now);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                word = password.getText().toString();
                sendRequestForLogin(name,word);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this,Password1_Activity.class));

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this,Register1_Activity.class));
            }
        });
    }



    /**
     * 这个方法用于向网络发送请求以验证登录
     */
    private void sendRequestForLogin(final String uname,final  String uword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/Login/checkIn/" + uname+"/"+uword)
                            .build();
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    Log.d("MySQL",jsonData);   //todo
                    User user = JSONUtil.parseUserWithGSON(jsonData);
                    loginForResult(user);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loginForResult(final User user){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(user!=null){
                    UserUtil.setOnlyUser(user);
                    finish();
                }else{
                    Toast.makeText(MyApplication.getContext(),"用户或密码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
