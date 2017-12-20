package com.example.administrator.zzudangdang.liangMade;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    RollPagerView mRollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        imageView = (ImageView) findViewById(R.id.homeimage1);
        imageView1 = (ImageView) findViewById(R.id.homeimage2);
        imageView2 = (ImageView) findViewById(R.id.homeimage3);
        imageView3 = (ImageView) findViewById(R.id.homeimage4);
        imageView4 = (ImageView) findViewById(R.id.homeimage5);
        imageView5 = (ImageView) findViewById(R.id.homeimage6);
        imageView6 = (ImageView) findViewById(R.id.homeimage7);
        imageView7 = (ImageView) findViewById(R.id.homeimage8);
        imageView8 = (ImageView) findViewById(R.id.homeimage9);

        Glide.with(this).load(R.drawable.home1).into(imageView);
        Glide.with(this).load(R.drawable.home2).into(imageView1);
        Glide.with(this).load(R.drawable.home3).into(imageView2);
        Glide.with(this).load(R.drawable.home4).into(imageView3);
        Glide.with(this).load(R.drawable.homelibrary).into(imageView4);
        Glide.with(this).load(R.drawable.homeclothes).into(imageView5);
        Glide.with(this).load(R.drawable.homesupermaket).into(imageView6);
        Glide.with(this).load(R.drawable.ddcommend).into(imageView7);
        Glide.with(this).load(R.drawable.homebaby).into(imageView8);
        roll();
    }

    void roll(){
        mRollViewPager = (RollPagerView) findViewById(R.id.home_roll_view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(700);
        //设置适配器
        mRollViewPager.setAdapter(new RollHomeAdapter());
        //隐藏指示器
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));
    }
}
