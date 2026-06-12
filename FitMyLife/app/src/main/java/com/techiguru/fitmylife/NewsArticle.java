package com.techiguru.fitmylife;

public class NewsArticle {
    private String title;
    private String description;
    private String imageUrl;
    private String category;

    public NewsArticle(String title, String description, String imageUrl, String category) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getCategory() { return category; }
}