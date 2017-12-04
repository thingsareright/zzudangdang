package com.example.administrator.zzudangdang.gaojiujiu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.adapter.BookAdapter;
import com.example.administrator.zzudangdang.dao.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookList_Activity extends AppCompatActivity {

    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        //TODO 下面的这些代码都是测试用的
        initBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.booklist_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter bookAdapter = new BookAdapter(bookList);
        recyclerView.setAdapter(bookAdapter);
    }

    /**TODO
     * 这里的init方法只是测试用的init方法，是需要更改和删除的
     */
    private void initBooks(){
        for (int i=0; i<20; i++) {
            Book book = new Book(1,"《人间炼狱》","胡歌","上午有印书馆",2,"FHAJSKLDKSLKFHASLKJLK",16,0,1,2,23.6f,"http://c.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c7ad3bf96f1f2b21192138a05.jpg");
            bookList.add(i, book);
        }
    }
}

