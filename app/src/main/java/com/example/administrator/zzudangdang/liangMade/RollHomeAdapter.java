package com.example.administrator.zzudangdang.liangMade;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.MyApplication;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by Administrator on 2017/9/6.
 */

public class RollHomeAdapter extends StaticPagerAdapter {

    private int[] imgs = {

            R.drawable.home2,
            R.drawable.home3,
            R.drawable.home1,
            R.drawable.home4,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        Glide.with(MyApplication.getContext()).load(imgs[position]).into(view);
//        Bitmap bit = BitmapFactory.decodeResource(MyApplication.getMyResources(),imgs[position]);
//        view.setImageBitmap(bit);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }

}