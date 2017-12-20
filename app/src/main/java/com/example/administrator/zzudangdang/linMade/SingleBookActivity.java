package com.example.administrator.zzudangdang.linMade;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.BookToClientforSingleBook;
import com.example.administrator.zzudangdang.singlebook.bookPagerAdapter_lj;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 沐莲心 on 2017/12/13.
 */

public class SingleBookActivity extends AppCompatActivity {
    /**
     * 为了方便全局更改，这里用到的所有要更改的控件都是类内公共的
     * TODO 还没有加上没有通过网络连接修改的控件
     */
    private List<String> tu_ljList = new ArrayList<>();
    private TextView book_name_lj;
    private TextView price_lj;
    private TextView writter_lj;
    private TextView press_lj;
    private ImageView boss_image_lj;
    private ImageView book_image1_lj;
    private ImageView book_image2_lj;
    private TextView boss_name_lj;
    private ViewPager viewPager;
    private List<View> lists = new ArrayList<View>();
    private com.example.administrator.zzudangdang.singlebook.bookPagerAdapter_lj bookPagerAdapter_lj;
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

    //这个对象封装了数据
    private BookToClientforSingleBook bookToClientforSingleBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        int bookid = getIntent().getIntExtra("bookid",1);
        int bossid = getIntent().getIntExtra("bossid",1);
        initData(bookid, bossid);


    }

    //初始化数据
    private void initData(int bookid, int bossid) {
        //进行网络请求
        sendRequestForSingleBook(bookid,bossid);
    }

    private void sendRequestForSingleBook(final int bookid, final int bossid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/book/bookForSingle?bookid=" + bookid)
                            .build();
                    System.out.println(ConstantUtil.getServer() + "/book/bookForSingle?bossid=" + bossid);
                    final Response response = client.newCall(request).execute();
                    final String jsonData = response.body().string();
                    if (jsonData == null)
                        return;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bookToClientforSingleBook = JSONUtil.parseSingleBookWithGSON(jsonData);
                            setDataForView(bookToClientforSingleBook);
                        }
                    });

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 这个方法用于把数据和View相关联
     * @param singleBook
     */
    private void setDataForView(BookToClientforSingleBook singleBook) {
        //关联recyclerview
        tu_ljList = singleBook.getImageAddressList();
        initAdapter();

        book_name_lj.setText(singleBook.getBook_name());
        price_lj.setText(singleBook.getPrice() + "");
        writter_lj.setText(singleBook.getWritter());
        press_lj.setText(singleBook.getPress());
        boss_name_lj.setText(singleBook.getBoss_name());
        Glide.with(this).load(singleBook.getBoss_image()).placeholder(R.mipmap.ic_launcher).into(boss_image_lj);

    }

    //初始化控件
    private void initView(){
        setContentView(R.layout.singlebook);

        book_name_lj = (TextView) findViewById(R.id.book_name_lj);
        price_lj = (TextView) findViewById(R.id.price_lj);
        writter_lj = (TextView) findViewById(R.id.writter_lj);
        press_lj = (TextView) findViewById(R.id.press_lj);
        boss_name_lj = (TextView) findViewById(R.id.boss_name_lj);
        boss_image_lj = (ImageView) findViewById(R.id.boss_image_lj);
        book_image1_lj =(ImageView) findViewById(R.id.book_image1_lj);
        book_image2_lj =(ImageView) findViewById(R.id.book_image2_lj);
        textView1 = (TextView) findViewById(R.id.thing);
        textView2 = (TextView) findViewById(R.id.introduce);
        textView3 = (TextView) findViewById(R.id.comments);
        textView4 = (TextView) findViewById(R.id.recommends);

        lists.add(getLayoutInflater().inflate(R.layout.page1_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page2_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page3_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page4_lj, null));
        bookPagerAdapter_lj = new bookPagerAdapter_lj(lists);
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

    //初始化Adapter
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    }


}