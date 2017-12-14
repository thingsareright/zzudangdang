package com.example.administrator.zzudangdang.dd01;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.zzudangdang.R;

import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/13.
 */

public class Tu_ljAdapter extends RecyclerView.Adapter<Tu_ljAdapter.ViewHolder> {
    private List<Tu_lj> mTu_ljList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tu_ljImage;

        public ViewHolder(View view) {
            super (view);
            tu_ljImage = (ImageView) view.findViewById(R.id.tu_lj_image);
        }
    }
    public  Tu_ljAdapter(List<Tu_lj> tu_ljList){
        mTu_ljList = tu_ljList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tu_item_lj, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Tu_lj tu_lj = mTu_ljList.get(position);
        holder.tu_ljImage.setImageResource(tu_lj.getImageId());
    }
    @Override
    public int getItemCount(){
        return mTu_ljList.size();
    }
}
