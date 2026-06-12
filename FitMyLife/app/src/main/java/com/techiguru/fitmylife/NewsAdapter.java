package com.techiguru.fitmylife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsArticle> newsArticles;

    public NewsAdapter(List<NewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsArticle article = newsArticles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDescription.setText(article.getDescription());
        
        Glide.with(holder.itemView.getContext())
                .load(article.getImageUrl())
                .placeholder(R.drawable.fitness)
                .into(holder.ivNewsImage);
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNewsImage;
        TextView tvTitle, tvDescription;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNewsImage = itemView.findViewById(R.id.iv_news_image);
            tvTitle = itemView.findViewById(R.id.tv_news_title);
            tvDescription = itemView.findViewById(R.id.tv_news_description);
        }
    }
}