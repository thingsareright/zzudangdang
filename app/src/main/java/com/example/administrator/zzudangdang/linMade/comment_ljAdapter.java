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
 * Created by 沐莲心 on 2017/12/19.
 */

public class comment_ljAdapter extends RecyclerView.Adapter<comment_ljAdapter.ViewHolder> {

    private List<Comment_lj> mcomment_ljList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView comment_ljUavatar;
        TextView comment_ljUname;
        TextView comment_ljScore;
        TextView comment_ljUcomment;
        TextView comment_ljTime;

        public ViewHolder(View view) {
            super(view);
            comment_ljUavatar = (ImageView) view.findViewById(R.id.Uavatar);
            comment_ljUname = (TextView) view.findViewById(R.id.Uname);
            comment_ljScore = (TextView) view.findViewById(R.id.score);
            comment_ljUcomment = (TextView) view.findViewById(R.id.Ucomment);
            comment_ljTime = (TextView) view.findViewById(R.id.time);
        }
    }

    public comment_ljAdapter(List<Comment_lj> comment_ljList) {
        mcomment_ljList = comment_ljList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_lj, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment_lj comment_lj = mcomment_ljList.get(position);
        holder.comment_ljUavatar.setImageResource(comment_lj.getUavatar());
        holder.comment_ljUname.setText(comment_lj.getUname());
        holder.comment_ljScore.setText(comment_lj.getScore());
        holder.comment_ljUcomment.setText(comment_lj.getUcomment());
        holder.comment_ljTime.setText(comment_lj.getTime());

    }

    @Override
    public int getItemCount() {
        return mcomment_ljList.size();
    }
}
