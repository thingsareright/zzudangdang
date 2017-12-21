package com.example.administrator.zzudangdang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.adapter.BookAdapterForResult;
import com.example.administrator.zzudangdang.adapter.BookAdapterForThink;
import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookList_Activity extends AppCompatActivity implements View.OnClickListener {

    private List<Book> bookListResult = new ArrayList<>();  //存储搜索结果的数据集
    private List<Book> bookListThink = new ArrayList<>();   //存储联想结果的数据集
    private RecyclerView recyclerViewForResult;     //展示结果的recyclerview
    private RecyclerView recyclerViewForThink;      //展示联想的recyclerview
    private static boolean flag = true; //true为线性布局，false为网格布局
    private Button searchView;        //搜索的文本框
    private EditText searchKeyWord;         //输入搜索的关键词的文本编辑框



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        recyclerViewForResult = (RecyclerView) findViewById(R.id.booklist_recycler_view_result);
        recyclerViewForThink = (RecyclerView) findViewById(R.id.booklist_recycler_view_think);

        Button button = (Button) findViewById(R.id.GeJv);
        button.setOnClickListener(this);

        searchView = (Button) findViewById(R.id.search);
        searchView.setOnClickListener(this);
        searchKeyWord = (EditText) findViewById(R.id.keyword);

        /*Log.e("error", "123");
        if (flag == true){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewForResult.setLayoutManager(layoutManager);
            BookAdapterForResult bookAdapterForResult = new BookAdapterForResult(bookListResult);
            recyclerViewForResult.setAdapter(bookAdapterForResult);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerViewForResult.setLayoutManager(gridLayoutManager);
            BookAdapterForThink bookAdapterForResult = new BookAdapterForThink(bookListResult);
            recyclerViewForResult.setAdapter(bookAdapterForResult);
        }*/


/*
        //下面加载联想的界面
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewForThink.setLayoutManager(gridLayoutManager);
        BookAdapterForThink bookAdapterForThink = new BookAdapterForThink(bookListThink);
        recyclerViewForThink.setAdapter(bookAdapterForThink);*/
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.GeJv:
                flag = !flag;
                if (flag == true){
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                    recyclerViewForResult.setLayoutManager(layoutManager);
                    BookAdapterForResult bookAdapterForResult = new BookAdapterForResult(bookListResult);
                    recyclerViewForResult.setAdapter(bookAdapterForResult);
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                    recyclerViewForResult.setLayoutManager(gridLayoutManager);
                    BookAdapterForThink bookAdapterForResult = new BookAdapterForThink(bookListResult);
                    recyclerViewForResult.setAdapter(bookAdapterForResult);
                }
                break;
            case R.id.search:
                sendRequestForBookResult(searchKeyWord.getText().toString());
                Log.d("search",searchKeyWord.getText().toString());
                System.out.println(searchKeyWord.getText().toString());
                break;
            default:
                break;
        }
    }

    /**
     * 这个方法用于向网络发送请求以刷新页面
     */
    private void sendRequestForBookResult(final String keyword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/book/bookForResult?name=" + keyword)
                            .build();
                    System.out.println(ConstantUtil.getServer() + "/book/bookForResult?name=" + keyword);
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    Log.d("MySQL",jsonData);   //todo
                    List<Book> books = JSONUtil.parseBookWithGSON(jsonData);
                    changeBookListForResult(books);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void changeBookListForResult(final List<Book> books){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bookListResult = books;
                if (flag == true){
                    LinearLayoutManager layoutManager = new LinearLayoutManager(BookList_Activity.this);
                    recyclerViewForResult.setLayoutManager(layoutManager);
                    BookAdapterForResult bookAdapterForResult = new BookAdapterForResult(bookListResult);
                    recyclerViewForResult.setAdapter(bookAdapterForResult);

                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(BookList_Activity.this, 2);
                    recyclerViewForResult.setLayoutManager(gridLayoutManager);
                    BookAdapterForThink bookAdapterForResult = new BookAdapterForThink(bookListResult);
                    recyclerViewForResult.setAdapter(bookAdapterForResult);
                }
            }
        });
    }
}

