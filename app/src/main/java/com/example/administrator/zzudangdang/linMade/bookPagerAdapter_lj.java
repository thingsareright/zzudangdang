package com.example.administrator.zzudangdang.linMade;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 沐莲心 on 2017/12/18.
 */

public class bookPagerAdapter_lj extends PagerAdapter {
    List<View> viewLists;
    private List<Comment_lj> comment_ljList = new ArrayList<>();

    public bookPagerAdapter_lj(List<View> lists) {
        viewLists = lists;
    }
    @Override
    public int getCount() {
        return viewLists.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(viewLists.get(position));
    }
    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager) view).addView(viewLists.get(position), 0);
        beOrigin();
        switch(position){
            case 0:
                SingleBookActivity.textView1.setTextSize(15);
                SingleBookActivity.textView1.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));
                break;
            case 1:
                SingleBookActivity.textView2.setTextSize(15);
                SingleBookActivity.textView2.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));
                break;
            case 2:
                SingleBookActivity.textView3.setTextSize(15);
                SingleBookActivity.textView3.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_lj);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
                recyclerView.setLayoutManager(layoutManager);
                initcomment_ljs();
                comment_ljAdapter adapter = new comment_ljAdapter(comment_ljList);
                recyclerView.setAdapter(adapter);

                break;
            case 3:
                SingleBookActivity.textView4.setTextSize(15);
                SingleBookActivity.textView4.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));
                break;
        }

        return viewLists.get(position);
    }
    void beOrigin(){
        SingleBookActivity.textView1.setTextSize(18);
        SingleBookActivity.textView2.setTextSize(18);
        SingleBookActivity.textView3.setTextSize(18);
        SingleBookActivity.textView4.setTextSize(18);
        SingleBookActivity.textView1.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBookActivity.textView2.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBookActivity.textView3.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBookActivity.textView4.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
    }
    private void initcomment_ljs() {
        Comment_lj lian = new Comment_lj(R.mipmap.ic_launcher_round, "1", "1", "1", "1");
        comment_ljList.add(lian);
    }
}
