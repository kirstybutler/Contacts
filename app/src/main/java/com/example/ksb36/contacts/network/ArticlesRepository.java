package com.example.ksb36.contacts.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.ksb36.contacts.model.Article;
import com.example.ksb36.contacts.model.ArticleList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesRepository {
    private Retrofit retrofit;
    private ArticlesService articlesService;
    private MutableLiveData<List<Article>> articleList;

    private static final ArticlesRepository ourInstance = new ArticlesRepository();

    public enum NetworkStatus {
        IDLE, LOADING, SUCCESS, ERROR
    }
    public MutableLiveData<NetworkStatus> networkStatus = new MutableLiveData<>();

    public static ArticlesRepository getInstance() {

        return ourInstance;
    }

    private ArticlesRepository() {

        networkStatus.setValue(NetworkStatus.IDLE);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ArticlesService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        articlesService = retrofit.create(ArticlesService.class);
    }

    public LiveData<List<Article>> getArticlelist() {
        articleList = new MutableLiveData<>();
        //final LiveData<List<Article>> data = new MutableLiveData<>();
        networkStatus.setValue(NetworkStatus.LOADING);

        Call<ArticleList> call = articlesService.getFirstArticles();
        call.enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                //data.setValue(response.body().getArticles());
                articleList.setValue(response.body().getArticles());

                networkStatus.setValue(NetworkStatus.IDLE);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                networkStatus.setValue(NetworkStatus.IDLE);

                t.printStackTrace();
            }
        });

        return articleList;
    }

    public LiveData<NetworkStatus> getNetworkStatus() {
        return networkStatus;
    }
}
