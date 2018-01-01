package com.example.administrator.zzudangdang.linMade;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/30.
 */

public class Comment0Adapter extends RecyclerView.Adapter<Comment0Adapter.ViewHolder> {
    private List<Comment0> mComment0List;
    private List<Comment1> comment1List = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment0B_name;
        RecyclerView comment0RecyclerView1_lj;
        View v;

        public ViewHolder(View view) {
            super(view);
            view = v;
            comment0B_name = (TextView) view.findViewById(R.id.B_name);
            comment0RecyclerView1_lj = (RecyclerView) view.findViewById(R.id.recycleview1_lj);
        }
    }

    public Comment0Adapter(List<Comment0> comment0List) {
        mComment0List = comment0List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview0_lj, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment0 comment0 = mComment0List.get(position);
        holder.comment0B_name.setText(comment0.getB_name());


        RecyclerView recyclerView = (RecyclerView) holder.v.findViewById(R.id.recycleview1_lj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);

        initcomment1();


        Comment1Adapter adapter = new Comment1Adapter(comment1List);
        recyclerView.setAdapter(adapter);
    }



    private void initcomment1() {
        Comment1 lian = new Comment1(R.drawable.book, "大数据【套装7册】为数据而生+大数据时代+数据之巅+决战大数据（升级版）:大数据的关键思考+爆发:大数据时代预见未来的新思维（经典版）+大数据营销 大数据时代三部曲 大数据思维与决策大数据之路", 358);
        comment1List.add(lian);
    }


    @Override
    public int getItemCount() {
        return mComment0List.size();
    }
}
