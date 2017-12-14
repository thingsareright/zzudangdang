package com.example.administrator.zzudangdang.singlebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.dao.entity.BookToClientforSingleBook;
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
    private RecyclerView recyclerView;
    private TextView book_name_lj;
    private TextView price_lj;
    private TextView writter_lj;
    private TextView press_lj;
    private ImageView boss_image_lj;
    private TextView boss_name_lj;

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
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lj);
    }

    //初始化Adapter
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Tu_ljAdapter adapter = new Tu_ljAdapter(tu_ljList);
        recyclerView.setAdapter(adapter);
    }


}