package com.example.ksb36.contacts.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ksb36.contacts.model.Article;
import com.example.ksb36.contacts.R;
import com.example.ksb36.contacts.model.ArticleList;
import com.example.ksb36.contacts.network.ArticlesRepository;

import java.util.List;

public class ContactListFragment extends Fragment {

    public RecyclerView contactListView;
    private LinearLayoutManager layoutManager;
    private ArticleListAdapter adapter;
    private ArticlesViewModel viewModel;
    private ProgressBar progressBar;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        contactListView = (RecyclerView) view.findViewById(R.id.contact_list_view);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactListView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        contactListView.addItemDecoration(divider);

        adapter = new ArticleListAdapter(listItemClickListener);
        contactListView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(ArticlesViewModel.class);
        viewModel.getArticleList().observe(this, articleListObserver);
        viewModel.getNetworkStatus().observe(this, networkStatusObserver);

        List<Article> data = viewModel.getArticleList().getValue();
        if (data != null) {
            adapter.updateData(data);
        }
        return view;
    }


    private final Observer<List<Article>> articleListObserver = new Observer<List<Article>>() {
        @Override
        public void onChanged(@Nullable List<Article> contacts) {
            adapter.updateData(contacts);
        }
    };

    private View.OnClickListener listItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            viewModel.setSelectedArticle(position);
        }
    };

    private final Observer<ArticlesRepository.NetworkStatus> networkStatusObserver = new Observer<ArticlesRepository.NetworkStatus>() {
        @Override
        public void onChanged(@Nullable ArticlesRepository.NetworkStatus networkStatus) {
            if (networkStatus == ArticlesRepository.NetworkStatus.LOADING)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        }
    };
}
