package com.techiguru.fitmylife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";
    private String category;

    public static NewsFragment newInstance(String category) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        List<NewsArticle> articles = getMockData(category);
        NewsAdapter adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);
        
        return view;
    }

    private List<NewsArticle> getMockData(String category) {
        List<NewsArticle> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(new NewsArticle(
                    category + " News Title " + i,
                    "This is a detailed description for " + category + " news item " + i + ". It covers the latest updates and happenings in this category.",
                    "https://picsum.photos/seed/" + category + i + "/400/200",
                    category
            ));
        }
        return list;
    }
}