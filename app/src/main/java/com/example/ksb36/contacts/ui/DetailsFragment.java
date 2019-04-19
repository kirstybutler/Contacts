package com.example.ksb36.contacts.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView articleDesc;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        articleTitle = (TextView) view.findViewById(R.id.title_text);
        articleDesc = (TextView) view.findViewById(R.id.desc_content);
        articleImage = (ImageView) view.findViewById(R.id.contact_image);
        articleAuthor = (TextView) view.findViewById(R.id.author_text);


        viewModel = ViewModelProviders.of(getActivity()).get(ArticlesViewModel.class);
        viewModel.getSelectedArticle().observe(this, selectedArticleObserver);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_fav:
                Toast.makeText(getActivity(), "Favourites selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateView(int position) {
        List<Article> articlesList = viewModel.getArticleList().getValue();
        if (articlesList == null) {
            return;
        }

        Article article = articlesList.get(position);

        articleTitle.setText(article.getTitle());
        articleAuthor.setText("Published by: " + article.getAuthor());
        articleDesc.setText(article.getDescription());

        Picasso.get()
                .load(article.getImageUrl())
                .resize(200,100)
                .centerCrop()
                .into(articleImage);

    }

    private final Observer<Integer> selectedArticleObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            updateView(integer);
        }
    };




}
