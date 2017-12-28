package com.example.administrator.zzudangdang.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.zzudangdang.R;

//未涉及到保存这个按钮，直接对radiobutton进行了监听，但是那个sexbutton.xml保存按钮未删除

public class SexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexbuttongjj);
        RadioButton radioButton1=(RadioButton)findViewById(R.id.radiomale) ;
        RadioButton radioButton2=(RadioButton)findViewById(R.id.radiofemale);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent();
                intent.putExtra("sex","男");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra("sex","女");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        findViewById(R.id.returnsex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
