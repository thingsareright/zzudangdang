package com.example.administrator.zzudangdang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.againstsky.verificationcode.VerificationCodeView;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.MyApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Register1_Activity extends AppCompatActivity {
    private Button Ok;
    private VerificationCodeView mVerificationCodeView;
    private EditText phone_edit;
    private EditText password_edit;
    private EditText myCheck;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.register_first);

        mVerificationCodeView = (VerificationCodeView) findViewById(R.id.register_code_view);
        phone_edit = (EditText) findViewById(R.id.register1_phone);
        password_edit = (EditText) findViewById(R.id.register1_password);
        myCheck = (EditText) findViewById(R.id.register1_mycheck);
        Ok = (Button) findViewById(R.id.register1_next);


        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myCheck.getText().toString().equals(mVerificationCodeView.getVerificationCode())){
                    phone = phone_edit.getText().toString();
                    password = password_edit.getText().toString();
                    Intent intent = new Intent(Register1_Activity.this,Register2_Activity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("password",password);
                    startActivity(intent);
                    sendRequestForGetCheck(phone);

                    finish();

                }else{
                    Toast.makeText(MyApplication.getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                }
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
}
