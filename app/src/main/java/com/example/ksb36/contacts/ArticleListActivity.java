package com.example.ksb36.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ksb36.contacts.ui.AboutFragment;
import com.example.ksb36.contacts.ui.ArticleListFragment;
import com.example.ksb36.contacts.ui.ArticlesViewModel;
import com.example.ksb36.contacts.ui.DetailsFragment;
import com.example.ksb36.contacts.ui.FacebookFragment;
import com.example.ksb36.contacts.ui.FavFragment;
import com.example.ksb36.contacts.ui.TwitterFragment;
import com.example.ksb36.contacts.ui.WebFragment;

public class ArticleListActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private ArticlesViewModel viewModel;
    private Boolean hasTwoPanes;
    private DrawerLayout drawer;
    public NavigationView navigationView;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //If device is rotated, or opened for first time
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                    new ArticleListFragment()).commit();
        }

        navigationView.setCheckedItem(R.id.nav_news);

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
        ArticleListFragment listFragment = new ArticleListFragment();
        transaction.replace(R.id.single_frag_placeholder, listFragment);
        transaction.commit();

        viewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        viewModel.getSelectedArticle().observe(this, selectedArticleObserver);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new ArticleListFragment()).commit();
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new FavFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_website:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new WebFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new AboutFragment()).commit();
                break;
            case R.id.nav_fb:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new FacebookFragment()).commit();
                break;
            case R.id.nav_twitter:
                getSupportFragmentManager().beginTransaction().replace(R.id.single_frag_placeholder,
                        new TwitterFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        //navigationView.setCheckedItem(menuItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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