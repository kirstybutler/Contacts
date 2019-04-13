package com.example.ksb36.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import android.arch.lifecycle.Observer;

public class ContactListActivity extends AppCompatActivity {

    public RecyclerView contactListView;
    private LinearLayoutManager layoutManager;
    private ContactListAdapter adapter;
    private ContactsViewModel viewModel;

    private final Observer<List<Contact>> contactListObserver = new Observer<List<Contact>>() {
        @Override
        public void onChanged(@Nullable List<Contact> contacts) {
            adapter.updateData(contacts);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        contactListView = (RecyclerView) findViewById(R.id.contact_list_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactListView.setLayoutManager(layoutManager);

        adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        viewModel.getContactList().observe(this, contactListObserver);

        viewModel.loadContacts();


    }


}
