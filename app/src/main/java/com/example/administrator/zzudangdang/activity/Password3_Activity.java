package com.example.administrator.zzudangdang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.User;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.MyApplication;
import com.example.administrator.zzudangdang.util.UserUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Password3_Activity extends AppCompatActivity {
    private Button next;
    private EditText new_password;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.password_retrieve3);

        phone = getIntent().getStringExtra("phone");

        next = (Button) findViewById(R.id.psw_retrieve3_button);
        new_password = (EditText) findViewById(R.id.password3_newpassword);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!new_password.getText().toString().trim().equals("")){
                    sendRequestForNewPassword(phone,new_password.getText().toString());
                }else{
                    Toast.makeText(MyApplication.getContext(),"新密码不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    /**
     * 这个方法用于向网络发送请求以修改新密码
     */
    private void sendRequestForNewPassword(final String uname,final  String uword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/changePassword/" + uname+"/"+uword)
                            .build();
                    client.newCall(request).execute();
                    newPasswordForResult();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void newPasswordForResult(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Password3_Activity.this,Login_Activity.class));
                finish();
            }
        });
    }
}
