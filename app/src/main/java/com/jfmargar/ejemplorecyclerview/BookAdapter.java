package com.jfmargar.ejemplorecyclerview;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    ArrayList<Book> books;
    int resource;

    public BookAdapter(Context context, ArrayList<Book> books, int resource) {
        this.context = context;
        this.books = books;
        this.resource = resource;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Esta línea siempre es igual
        View itemView = LayoutInflater.from(context).inflate(resource, viewGroup, false);

        BookViewHolder bookViewHolder = new BookViewHolder(itemView);

        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        //Saco el libro de la lista que está en la posición "i"
        Book book = books.get(i);
        //lo uso para rellenar el viewholder
        bookViewHolder.bindBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;
        TextView tvSubtitle;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
        }

        public void bindBook(Book book) {
            tvTitle.setText(book.getTitle());
            tvSubtitle.setText(book.getSubtitle());

            Glide.with(context).load(book.getUrlCover()).into(ivCover);
        }
    }
}
