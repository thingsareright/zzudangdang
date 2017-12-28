package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;


public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailbuttongjj);
        final TextView editemail=(TextView) findViewById(R.id.editEmail);
        findViewById(R.id.saveEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editemail.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    Intent intent =new Intent();
                    intent.putExtra("e_mail",email);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });
        findViewById(R.id.returnemail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
