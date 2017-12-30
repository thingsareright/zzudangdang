package com.example.administrator.zzudangdang.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.myinfo.MyInfoActivity;


public class UserInformation_Activity extends AppCompatActivity {
    //这只一个toolbar，单位显示上面的文字

    ImageButton head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
       // Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        head = (ImageButton) findViewById(R.id.head);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInformation_Activity.this, MyInfoActivity.class));
            }
        });

    }



}
