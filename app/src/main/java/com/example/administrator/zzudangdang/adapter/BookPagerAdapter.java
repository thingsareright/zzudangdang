package com.example.administrator.zzudangdang.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.activity.SingleBook_Activity;
import com.example.administrator.zzudangdang.dao.entity.BookToClientforSingleBook;
import com.example.administrator.zzudangdang.dao.entity.Comment;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 沐莲心 on 2017/12/18.
 */

public class BookPagerAdapter extends PagerAdapter {
    List<View> viewLists;
    private List<Comment> comment_List = new ArrayList<>();
    private TextView book_name_lj;
    private TextView price_lj;
    private TextView writter_lj;
    private TextView press_lj;
    private ImageView boss_image_lj;
    private ImageView book_image1_lj;
    private ImageView book_image2_lj;
    private TextView boss_name_lj;
    private List<String> tu_ljList = new ArrayList<>();
    private Activity activity = null;

    public BookPagerAdapter(List<View> lists, Activity activity) {
        viewLists = lists;
        this.activity = activity;
        initcomment_ljs();initcomment_ljs();
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

//        beOrigin();
        switch(position){
            case 0:
//                SingleBook_Activity.textView1.setTextSize(20);
//                SingleBook_Activity.textView1.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));

                book_name_lj = (TextView) view.findViewById(R.id.book_name_lj);
                price_lj = (TextView) view.findViewById(R.id.price_lj);
                writter_lj = (TextView) view.findViewById(R.id.writter_lj);
                press_lj = (TextView) view.findViewById(R.id.press_lj);
                boss_name_lj = (TextView) view.findViewById(R.id.boss_name_lj);
                boss_image_lj = (ImageView) view.findViewById(R.id.boss_image_lj);
                book_image1_lj =(ImageView) view.findViewById(R.id.book_image1_lj);
                book_image2_lj =(ImageView) view.findViewById(R.id.book_image2_lj);
                initData(SingleBook_Activity.bookid, SingleBook_Activity.bossid);

                break;
            case 1:
//                SingleBook_Activity.textView2.setTextSize(20);
//                SingleBook_Activity.textView2.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));
                break;
            case 2:
//                SingleBook_Activity.textView3.setTextSize(20);
//                SingleBook_Activity.textView3.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_lj);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
                recyclerView.setLayoutManager(layoutManager);

                CommentAdapter adapter = new CommentAdapter(comment_List);
                recyclerView.setAdapter(adapter);

                break;
            case 3:
//                SingleBook_Activity.textView4.setTextSize(20);
//                SingleBook_Activity.textView4.setTextColor(MyApplication.getResource().getColor(R.color.colorSelectedIcon));
                break;
        }

        return viewLists.get(position);
    }
    void beOrigin(){
        SingleBook_Activity.textView1.setTextSize(18);
        SingleBook_Activity.textView2.setTextSize(18);
        SingleBook_Activity.textView3.setTextSize(18);
        SingleBook_Activity.textView4.setTextSize(18);
        SingleBook_Activity.textView1.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBook_Activity.textView2.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBook_Activity.textView3.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
        SingleBook_Activity.textView4.setTextColor(MyApplication.getResource().getColor(R.color.color_text_log));
    }
    private void initcomment_ljs() {
        Comment lian = new Comment(R.mipmap.ic_launcher_round, "1", "1", "1", "1");
        comment_List.add(lian);
    }

    //初始化Adapter
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    }
    /**
     * 这个方法用于把数据和View相关联
     * @param singleBook
     */
    private void setDataForView(BookToClientforSingleBook singleBook) {
        //关联recyclerview
        tu_ljList = singleBook.getImageAddressList();
        initAdapter();

        book_name_lj.setText(singleBook.getBook_name());
        price_lj.setText(singleBook.getPrice() + "");
        writter_lj.setText(singleBook.getWritter());
        press_lj.setText(singleBook.getPress());
        boss_name_lj.setText(singleBook.getBoss_name());
        Glide.with(MyApplication.getContext()).load(singleBook.getBoss_image()).placeholder(R.mipmap.ic_launcher).into(boss_image_lj);

    }

    //初始化数据
    private void initData(int bookid, int bossid) {
        //进行网络请求
        sendRequestForSingleBook(bookid,bossid);
    }

    private void sendRequestForSingleBook(final int bookid, final int bossid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/book/bookForSingle?bookid=" + bookid+"&bossid="+bossid)
                            .build();
                    System.out.println(ConstantUtil.getServer() + "/book/bookForSingle?bossid=" + bossid);
                    final Response response = client.newCall(request).execute();
                    final String jsonData = response.body().string();
                    if (jsonData == null)
                        return;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BookToClientforSingleBook bookToClientforSingleBook = JSONUtil.parseSingleBookWithGSON(jsonData);
                            setDataForView(bookToClientforSingleBook);
                        }
                    });

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
