package com.example.administrator.zzudangdang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.againstsky.verificationcode.VerificationCodeView;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.User;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.MyApplication;
import com.example.administrator.zzudangdang.util.UserUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Password1_Activity extends AppCompatActivity {

    private Button Ok;
    private VerificationCodeView mVerificationCodeView;
    private EditText phone_edit;
    private EditText myCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.password_retrieve1);

        mVerificationCodeView = (VerificationCodeView) findViewById(R.id.password_code_view);
        phone_edit = (EditText) findViewById(R.id.password1_phone);
        myCheck = (EditText) findViewById(R.id.password1_mycheck);
        Ok = (Button) findViewById(R.id.psw_retrieve1_button);


        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myCheck.getText().toString().equals(mVerificationCodeView.getVerificationCode())){
                    Intent intent = new Intent(Password1_Activity.this,Password2_Activity.class);
                    intent.putExtra("phone",phone_edit.getText().toString());
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(MyApplication.getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
