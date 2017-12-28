package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;


public class QQActivity extends AppCompatActivity {

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
                    Intent intent =new Intent();
                    intent.putExtra("qqNo",QQNo);
                    setResult(RESULT_OK,intent);
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
}