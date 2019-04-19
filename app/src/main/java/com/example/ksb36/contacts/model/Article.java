package com.example.ksb36.contacts.model;

import com.google.gson.annotations.Expose;

public class Article {
    @Expose
    private String title;
    @Expose
    private String author;
    @Expose
    private String description;
    @Expose
    private String articleUrl;
    @Expose
    private String imageUrl;
    @Expose
    private String dateTime;

    public Article(String title, String author, String description, String articleURL, String imageURL, String dateTime) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.articleUrl = articleURL;
        this.imageUrl = imageURL;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleURL) {
        this.articleUrl = articleURL;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageURL(String imageURL) {
        this.imageUrl = imageURL;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}