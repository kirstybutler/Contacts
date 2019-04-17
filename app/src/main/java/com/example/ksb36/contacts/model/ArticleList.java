package com.example.ksb36.contacts.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ArticleList {

    @Expose
    private Integer page;
    @Expose
    private Integer category;
    @Expose
    private List<Article> articles;
    @Expose
    private Integer year;
    @Expose
    private Integer month;
    @Expose
    private Integer day;

    public ArticleList(Integer page, Integer category, List<Article> articles, Integer year, Integer month, Integer day) {
        this.page = page;
        this.category = category;
        this.articles = articles;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
