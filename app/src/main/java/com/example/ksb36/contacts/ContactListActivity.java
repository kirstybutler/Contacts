package com.example.ksb36.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        ContactListFragment listFragment = new ContactListFragment();
        transaction.replace(R.id.single_frag_placeholder, listFragment);
        transaction.commit();

        viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        viewModel.getSelectedContact().observe(this, selectedContactObserver);

        viewModel.loadContacts();
    }

    private void showDetailsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        DetailsFragment detailsFragment = new DetailsFragment();
        transaction.replace(R.id.single_frag_placeholder, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private final Observer<Integer> selectedContactObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            showDetailsFragment();
            Toast.makeText(getApplicationContext(), "Clicked " + viewModel.getSelectedContact(),
                    Toast.LENGTH_LONG).show();
        }
    };
}
