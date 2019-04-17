package com.example.ksb36.contacts.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ksb36.contacts.model.Article;
import com.example.ksb36.contacts.model.ArticleList;
import com.example.ksb36.contacts.network.ArticlesRepository;

import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private LiveData<List<Article>> articleList;

    private MutableLiveData<Integer> selectedArticle = new MutableLiveData<Integer>();


    public LiveData<Integer> getSelectedArticle() {

        return selectedArticle;
    }

    public void setSelectedArticle(int position) {

        selectedArticle.setValue(position);
    }

    public LiveData<List<Article>> getArticleList() {

        if (articleList == null) {
            articleList = ArticlesRepository.getInstance().getArticlelist();
        }

        return articleList;
    }

    public LiveData<ArticlesRepository.NetworkStatus> getNetworkStatus() {
        return ArticlesRepository.getInstance().getNetworkStatus();
    }
}
