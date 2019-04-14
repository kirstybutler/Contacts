package com.example.ksb36.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class ContactListFragment extends Fragment {

    public RecyclerView contactListView;
    private LinearLayoutManager layoutManager;
    private ContactListAdapter adapter;
    private ContactsViewModel viewModel;

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

        contactListView = (RecyclerView) view.findViewById(R.id.contact_list_view);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactListView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        contactListView.addItemDecoration(divider);

        adapter = new ContactListAdapter(listItemClickListener);
        contactListView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        viewModel.getContactList().observe(this, contactListObserver);

        //viewModel.loadContacts();

        List<Contact> data = viewModel.getContactList().getValue();
        if (data != null) {
            adapter.updateData(data);
        }
        return view;
    }


    private final Observer<List<Contact>> contactListObserver = new Observer<List<Contact>>() {
        @Override
        public void onChanged(@Nullable List<Contact> contacts) {
            adapter.updateData(contacts);
        }
    };

    private View.OnClickListener listItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            List<Contact> list = viewModel.getContactList().getValue();
            Contact contact = list.get(position);

            viewModel.setSelectedContact(position);

            //Toast.makeText(v.getContext(), "Clicked " + contact.getFirstName() + " " + contact.getLastName(),
                    //Toast.LENGTH_LONG).show();
        }
    };
}
