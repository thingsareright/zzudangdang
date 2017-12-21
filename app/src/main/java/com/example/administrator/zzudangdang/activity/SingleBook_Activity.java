package com.example.administrator.zzudangdang.activity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.BookToClientforSingleBook;
import com.example.administrator.zzudangdang.adapter.BookPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/13.
 */

public class SingleBook_Activity extends AppCompatActivity {
    /**
     * 为了方便全局更改，这里用到的所有要更改的控件都是类内公共的
     * TODO 还没有加上没有通过网络连接修改的控件
     */
    private ViewPager viewPager;
    private List<View> lists = new ArrayList<View>();
    private BookPagerAdapter bookPagerAdapter_lj;
    private Bitmap cursor_lj;
    private int offSet;
    public static TextView textView1;
    public static TextView textView2;
    public static TextView textView3;
    public static TextView textView4;
    private int currentItem;
    private int bmpW;
    private ImageView imageView;
    private Animation animation;
    private Matrix matrix = new Matrix();
    public static int bookid;
    public static int bossid;

    //这个对象封装了数据
    private BookToClientforSingleBook bookToClientforSingleBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        bookid = getIntent().getIntExtra("bookid",1);
        bossid = getIntent().getIntExtra("bossid",1);

    }

    //初始化控件
    private void initView(){
        setContentView(R.layout.activity_singlebook);

        textView1 = (TextView) findViewById(R.id.thing);
        textView2 = (TextView) findViewById(R.id.introduce);
        textView3 = (TextView) findViewById(R.id.comments);
        textView4 = (TextView) findViewById(R.id.recommends);

        lists.add(getLayoutInflater().inflate(R.layout.singlebook_page1_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.singlebook_page2_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.singlebook_page3_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.singlebook_page4_lj, null));
        bookPagerAdapter_lj = new BookPagerAdapter(lists,this);
        viewPager = (ViewPager) findViewById(R.id.viewpager_lj);
        viewPager.setAdapter(bookPagerAdapter_lj);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = offSet * 2 + bmpW;
            int two = one * 2;
            int three = one * 3;

            @Override
            public void onPageSelected(int arg0) {
                Animation animation = null;
                switch (arg0) {
                    case 0:
                        if (currentItem == 1) {
                            animation = new TranslateAnimation(one, 0, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, 0, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, one, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, one, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, one, 0, 0);
                        }
                        break;
                    case 2:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, two, 0, 0);
                        } else if (currentItem == 1) {
                            animation = new TranslateAnimation(one, two, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, two, 0, 0);
                        }
                        break;
                    case 3:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, three, 0, 0);
                        }
                        else if (currentItem == 1) {
                            animation = new TranslateAnimation(one, three, 0, 0);
                        }
                        else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, three, 0, 0);
                        }
                        break;
                }


                currentItem = arg0;
                animation.setDuration(300);
                animation.setFillAfter(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }


            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }

        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(3);
            }

        });
    }




}