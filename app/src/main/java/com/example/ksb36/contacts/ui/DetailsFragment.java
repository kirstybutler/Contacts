package com.example.ksb36.contacts.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksb36.contacts.model.Article;
import com.example.ksb36.contacts.R;
import com.example.ksb36.contacts.model.ArticleList;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DetailsFragment extends Fragment {

    private ArticlesViewModel viewModel;


    private ImageView articleImage;
    private TextView articleTitle;
    private TextView articleAuthor;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        articleTitle = (TextView) view.findViewById(R.id.contact_name);
        articleAuthor = (TextView) view.findViewById(R.id.contact_phone);
        articleImage = (ImageView) view.findViewById(R.id.contact_image);

        viewModel = ViewModelProviders.of(getActivity()).get(ArticlesViewModel.class);
        viewModel.getSelectedArticle().observe(this, selectedArticleObserver);

        return view;
    }

    public void updateView(int position) {
        List<Article> articlesList = viewModel.getArticleList().getValue();
        if (articlesList == null) {
            return;
        }

        Article article = articlesList.get(position);

        articleTitle.setText(article.getTitle());
        articleAuthor.setText(article.getAuthor());

        Picasso.get()
                .load(article.getImageURL())
                .resize(50,50)
                .centerCrop()
                .into(articleImage);

        //contactImage.setImageResource(contact.getImageResource());
    }

    private final Observer<Integer> selectedArticleObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            updateView(integer);
        }
    };
}
