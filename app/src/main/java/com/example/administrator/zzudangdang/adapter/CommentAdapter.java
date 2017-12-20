package com.example.administrator.zzudangdang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Comment;

import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/19.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> mcomment_List;

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

    public CommentAdapter(List<Comment> comment_List) {
        mcomment_List = comment_List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlebook_recyclerview_lj, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment_ = mcomment_List.get(position);
        holder.comment_ljUavatar.setImageResource(comment_.getUavatar());
        holder.comment_ljUname.setText(comment_.getUname());
        holder.comment_ljScore.setText(comment_.getScore());
        holder.comment_ljUcomment.setText(comment_.getUcomment());
        holder.comment_ljTime.setText(comment_.getTime());

    }

    @Override
    public int getItemCount() {
        return mcomment_List.size();
    }
}
