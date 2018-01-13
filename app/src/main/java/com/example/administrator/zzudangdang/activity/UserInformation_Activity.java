package com.example.administrator.zzudangdang.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.myinfo.MyInfoActivity;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.UserUtil;


public class UserInformation_Activity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;

    ImageButton head;
    Button order;
    TextView phone;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
       // Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.UserInformationRefresh);
        head = (ImageButton) findViewById(R.id.head);
        order = (Button) findViewById(R.id.user_myorder);
        phone = (TextView) findViewById(R.id.UserInformation_phone);

        swipeRefreshLayout.setColorSchemeColors(R.color.red);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
            }
        });

        initView();

    }

    private void initView() {
        phone.setText(UserUtil.getOnlyUser().getPhone()+"\n普通会员");
        if(UserUtil.getOnlyUser().getHead()!=null){
            String headuri = ConstantUtil.getServer() + "/" + UserUtil.getOnlyUser().getHead();
            Glide.with(getApplication()).load(ConstantUtil.getServer()+"/"+UserUtil.getOnlyUser().getHead()).diskCacheStrategy( DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(R.mipmap.yinlin).into(head);
        }
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInformation_Activity.this, MyInfoActivity.class));
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInformation_Activity.this, Order_Acticity.class));
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }


}
