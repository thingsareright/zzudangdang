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
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.MyApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Register2_Activity extends AppCompatActivity {
    private Button next;
    private EditText check;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.register_second);


        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");

        next = (Button) findViewById(R.id.register2_button);
        check = (EditText) findViewById(R.id.register2_mycheck);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!check.getText().toString().trim().equals("")){
                    sendRequestForCheck(phone,password,check.getText().toString());
                }else{
                    Toast.makeText(MyApplication.getContext(),"验证码不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    /**
     * 这个方法用于向网络发送请求以验证验证码是否正确
     */
    private void sendRequestForCheck(final String phone,final String password,final  String usercode) {
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
                        request = new Request.Builder()
                                .url(ConstantUtil.getServer() + "/User/registerUser/" + phone+"/"+password)
                                .build();
                        client.newCall(request).execute();
                        checkForResult();
                    }else{
                        Toast.makeText(Register2_Activity.this, "输入验证码错误", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Register2_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
