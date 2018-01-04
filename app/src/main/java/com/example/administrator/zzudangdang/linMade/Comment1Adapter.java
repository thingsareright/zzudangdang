package com.example.administrator.zzudangdang.linMade;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;

import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/30.
 */

public class Comment1Adapter extends RecyclerView.Adapter<Comment1Adapter.ViewHolder> {
    private List<Comment1> mComment1List;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView comment1Bimage;
        TextView comment1Bname;
        TextView comment1Bprice;

        public ViewHolder(View view) {
            super(view);
            comment1Bimage = (ImageView) view.findViewById(R.id.Bimage);
            comment1Bname = (TextView) view.findViewById(R.id.Bname);
            comment1Bprice = (TextView) view.findViewById(R.id.Bprice);
        }
    }

    public Comment1Adapter(List<Comment1> comment1List) {
        mComment1List = comment1List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1_lj, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment1 comment1 = mComment1List.get(position);
        holder.comment1Bimage.setImageResource(comment1.getBimage());
        holder.comment1Bname.setText(comment1.getBname());
        holder.comment1Bprice.setText(comment1.getBprice());
    }


    @Override
    public int getItemCount() {
        return mComment1List.size();
    }
}
