package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;


public class WechatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechatbuttongjj);

        final TextView editWechat = (TextView)findViewById(R.id.editwechat);
        findViewById(R.id.saveWechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wechatno = editWechat.getText().toString();
                if(!TextUtils.isEmpty(wechatno)){
                    Intent intent = new Intent();
                    intent.putExtra("wechatNo", wechatno);
                    setResult(RESULT_OK, intent);
                    finish();}
            }
        });
        findViewById(R.id.returnwechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
