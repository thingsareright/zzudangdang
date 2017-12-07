package com.example.administrator.zzudangdang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class BookAdapterForThink extends RecyclerView.Adapter<BookAdapterForThink.ViewHolder> {

    private List<Book> bookList;

    public BookAdapterForThink(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_book_think,parent,false);
        BookAdapterForThink.ViewHolder holder = new BookAdapterForThink.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = bookList.get(position);
        //图片采用Glide添加，注意检查这里的context参数对不对
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
}
