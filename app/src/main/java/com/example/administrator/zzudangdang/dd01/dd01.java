package com.example.administrator.zzudangdang.dd01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.zzudangdang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/13.
 */

public class dd01 extends AppCompatActivity {
    private List<Tu_lj> tu_ljList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangpinye_dd01);
        initTu_ljs();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Tu_ljAdapter adapter = new Tu_ljAdapter(tu_ljList);
        recyclerView.setAdapter(adapter);
    }

    private void initTu_ljs(){
        Tu_lj tu1 = new Tu_lj(R.drawable.book);
        tu_ljList.add(tu1);
        Tu_lj tu2 = new Tu_lj(R.drawable.book1);
        tu_ljList.add(tu2);
    }
}