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

public class Password2_Activity extends AppCompatActivity {

    private Button next;
    private TextView getCheck;
    private EditText check;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.password_retrieve2);

        phone = getIntent().getStringExtra("phone");

        next = (Button) findViewById(R.id.psw_retrieve2_button);
        getCheck = (TextView) findViewById(R.id.psw_retrieve2_button);
        check = (EditText) findViewById(R.id.password2_getCheck);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!check.getText().toString().equals("")){
                    sendRequestForCheck(phone,check.getText().toString());
                }else{
                    Toast.makeText(MyApplication.getContext(),"验证码不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
        getCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequestForGetCheck(phone);
            }
        });

    }


    private void sendRequestForGetCheck(final String phone) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/sms/getToken?phone=" + phone)
                            .build();
                    client.newCall(request).execute();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 这个方法用于向网络发送请求以验证验证码是否正确
     */
    private void sendRequestForCheck(final String phone,final  String usercode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/sms/verifyTokenCode?phone=" + phone+"&usercode="+usercode)
                            .build();
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    Log.d("MySQL",jsonData);   //todo
                    if(jsonData.equals("1")){
                        checkForResult();
                    }else{
                        Toast.makeText(Password2_Activity.this, "输入验证码错误", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkForResult(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Password2_Activity.this,Password2_Activity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
                finish();

            }
        });
    }
}
