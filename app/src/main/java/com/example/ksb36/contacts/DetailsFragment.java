package com.example.ksb36.contacts;


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
import android.widget.Toast;

import java.util.List;


public class DetailsFragment extends Fragment {

    private ContactsViewModel viewModel;

    private ImageView contactImage;
    private TextView contactName;
    private TextView contactPhone;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        contactName = (TextView) view.findViewById(R.id.contact_name);
        contactPhone = (TextView) view.findViewById(R.id.contact_phone);
        contactImage = (ImageView) view.findViewById(R.id.contact_image);

        viewModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        viewModel.getSelectedContact().observe(this, selectedContactObserver);

        return view;
    }

    public void updateView(int position) {
        List<Contact> contacts = viewModel.getContactList().getValue();
        if (contacts == null) {
            return;
        }
        Contact contact = contacts.get(position);

        contactName.setText(contact.getFirstName() + " " + contact.getLastName());
        contactPhone.setText(contact.getPhoneNumber());
        contactImage.setImageResource(contact.getImageResource());
    }

    private final Observer<Integer> selectedContactObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            updateView(integer);
        }
    };
}
