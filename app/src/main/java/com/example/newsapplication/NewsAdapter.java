package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.security.PrivilegedAction;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Articles> articlesArrayList;

    public NewsAdapter(Context context, ArrayList<Articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
   Articles articles=articlesArrayList.get(position);
   holder.newsTVHeading.setText(articles.getTitle());
   holder.newsTVSubHeading.setText(articles.getDescription());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Activity2.class);
                intent.putExtra("url",articles.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView newsIV;
        private TextView newsTVHeading,newsTVSubHeading;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsIV=itemView.findViewById(R.id.newsIV);
            newsTVHeading=itemView.findViewById(R.id.newsTVHead);
            newsTVSubHeading=itemView.findViewById(R.id.newsSubHead);

        }
    }
}
