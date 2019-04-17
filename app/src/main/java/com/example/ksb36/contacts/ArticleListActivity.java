package com.example.ksb36.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import com.example.ksb36.contacts.ui.ContactListFragment;
import com.example.ksb36.contacts.ui.ArticlesViewModel;
import com.example.ksb36.contacts.ui.DetailsFragment;

public class ArticleListActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private ArticlesViewModel viewModel;
    private Boolean hasTwoPanes;

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        if (findViewById(R.id.detail_frag_placeholder) == null) {
            hasTwoPanes = false;
        } else {
            hasTwoPanes = true;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            DetailsFragment detailsFragment = new DetailsFragment();
            transaction.replace(R.id.detail_frag_placeholder, detailsFragment);
            transaction.commit();

        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ContactListFragment listFragment = new ContactListFragment();
        transaction.replace(R.id.single_frag_placeholder, listFragment);
        transaction.commit();

        viewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        viewModel.getSelectedArticle().observe(this, selectedArticleObserver);
    }

    private void showDetailsFragment() {
        Fragment previousFragment = getSupportFragmentManager().findFragmentById(R.id.single_frag_placeholder);
        DetailsFragment detailsFragment = new DetailsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Slide exitAnim = new Slide();
        exitAnim.setSlideEdge(Gravity.LEFT);
        exitAnim.setDuration(250);
        Slide enterAnim = new Slide();
        enterAnim.setSlideEdge(Gravity.RIGHT);
        enterAnim.setDuration(250);

        previousFragment.setExitTransition(exitAnim);
        previousFragment.setEnterTransition(enterAnim);

        transaction.replace(R.id.single_frag_placeholder, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void shouldDisplayHomeUp() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    private final Observer<Integer> selectedArticleObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            if (!hasTwoPanes) {
                showDetailsFragment();
            }
        }
    };
}