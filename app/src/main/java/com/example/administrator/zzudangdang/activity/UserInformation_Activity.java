package com.example.administrator.zzudangdang.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.myinfo.MyInfoActivity;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.UserUtil;


public class UserInformation_Activity extends AppCompatActivity {
    //这只一个toolbar，单位显示上面的文字

    ImageButton head;
    Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
       // Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        head = (ImageButton) findViewById(R.id.head);
        phone = (Button) findViewById(R.id.UserInformation_phone);
        if(UserUtil.getOnlyUser().getHead()!=null){
            Glide.with(this).load(ConstantUtil.getServer()+"/"+UserUtil.getOnlyUser().getHead()).into(head);
        }

        phone.setText(UserUtil.getOnlyUser().getPhone()+"\n普通会员");

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInformation_Activity.this, MyInfoActivity.class));
            }
        });

    }



}
