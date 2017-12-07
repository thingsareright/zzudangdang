package com.example.administrator.zzudangdang.booklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.adapter.BookAdapterForResult;
import com.example.administrator.zzudangdang.adapter.BookAdapterForThink;
import com.example.administrator.zzudangdang.dao.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookList_Activity extends AppCompatActivity implements View.OnClickListener {

    private List<Book> bookListResult = new ArrayList<>();  //存储搜索结果的数据集
    private List<Book> bookListThink = new ArrayList<>();   //存储联想结果的数据集
    private RecyclerView recyclerViewForResult;     //展示结果的recyclerview
    private RecyclerView recyclerViewForThink;      //展示联想的recyclerview
    private static boolean flag = true; //true为线性布局，false为网格布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        //TODO 下面的这些代码都是测试用的
        initBooks();
        recyclerViewForResult = (RecyclerView) findViewById(R.id.booklist_recycler_view_result);
        recyclerViewForThink = (RecyclerView) findViewById(R.id.booklist_recycler_view_think);

        Button button = (Button) findViewById(R.id.GeJv);
        button.setOnClickListener(this);

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




        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewForThink.setLayoutManager(gridLayoutManager);
        BookAdapterForThink bookAdapterForThink = new BookAdapterForThink(bookListThink);
        recyclerViewForThink.setAdapter(bookAdapterForThink);
    }

    /**TODO
     * 这里的init方法只是测试用的init方法，是需要更改和删除的
     */
    private void initBooks(){
        for (int i=0; i<20; i++) {
            Book book = new Book(1,"《人间炼狱》","胡歌","上午有印书馆",2,"FHAJSKLDKSLKFHASLKJLK",16,0,1,2,23.6f,"http://www.qqkubao.com/uploadfile/2014/10/1/20141011173953149.jpg");
            bookListResult.add(i, book);
            bookListThink.add(i,book);
        }
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
            default:
                break;
        }
    }
}

