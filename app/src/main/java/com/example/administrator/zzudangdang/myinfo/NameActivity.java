package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;


/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class NameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nicknamebuttongjj);

        final TextView editName = (TextView)findViewById(R.id.editname);
        findViewById(R.id.saveNickname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                if(!TextUtils.isEmpty(name)){
                    Intent intent = new Intent();
                    intent.putExtra("nickname", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        findViewById(R.id.returnname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
