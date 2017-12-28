package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;


public class SelfInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfbuttongjj);
        final TextView editSelf=(TextView) findViewById(R.id.editDescribe);
        findViewById(R.id.saveDescribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Describe=editSelf.getText().toString();
                if (!TextUtils.isEmpty(Describe)) {
                    Intent intent =new Intent();
                    intent.putExtra("describe",Describe);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
        findViewById(R.id.returnself).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
