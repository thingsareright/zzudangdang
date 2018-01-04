package com.example.administrator.zzudangdang.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.activity.SingleBook_Activity;
import com.example.administrator.zzudangdang.dao.entity.Book;
import com.example.administrator.zzudangdang.util.MyApplication;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2017/12/4 0004.
 * 这个适配器用于展示通过搜索结果进行搜索的界面
 */

public class BookAdapterForResult extends RecyclerView.Adapter<BookAdapterForResult.ViewHolder> {

    private  List<Book> bookList;
    private View view;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_book_result,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book book = bookList.get(position);
        //图片采用Glide添加，注意检查这里的context参数对不对
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bookIntent = new Intent(MyApplication.getContext(), SingleBook_Activity.class);
                bookIntent.putExtra("bookid",book.getId());
                bookIntent.putExtra("bossid",book.getBoss_id());
                bookIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(bookIntent);

            }
        });
        Glide.with(holder.book_picture.getContext()).load(book.getBook_picture()).placeholder(R.mipmap.ic_launcher).into(holder.book_picture);
        holder.book_name.setText(book.getBook_name());
        holder.book_writter.setText(book.getWritter());
        //好评率和评价人数以后随机生成
        holder.percent_peoplenumber.setText((int)((Math.random())* 1000)/10.0 + "%好评" + "（" + (int)Math.random() * 100 + "人)");
        holder.book_price.setText("" + book.getBook_price() + "");  //必须要有前面后面两个引号
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView book_picture;
        TextView book_name;
        TextView book_writter;
        TextView book_price;
        TextView percent_peoplenumber;

        public ViewHolder(View itemView) {
            super(itemView);
            book_picture = (ImageView) itemView.findViewById(R.id.book_picture);
            book_name = (TextView) itemView.findViewById(R.id.book_name);
            book_writter = (TextView) itemView.findViewById(R.id.book_writter);
            book_price = (TextView) itemView.findViewById(R.id.book_price);
            percent_peoplenumber = (TextView) itemView.findViewById(R.id.percent_peoplenumber);

        }


    }

    public BookAdapterForResult(List<Book> bookList) {
        this.bookList = bookList;
    }


}
