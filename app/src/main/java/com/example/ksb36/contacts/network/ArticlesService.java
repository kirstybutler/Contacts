package com.example.ksb36.contacts.network;
import com.example.ksb36.contacts.model.ArticleList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticlesService {

    static final String BASE_URL = "http://www.ubicomp-kent.org/projects/newsfeed/";

    @GET("articles.cgi")
    Call<ArticleList> getFirstArticles();
}