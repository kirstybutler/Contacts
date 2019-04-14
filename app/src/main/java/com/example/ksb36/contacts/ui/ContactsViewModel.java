package com.example.ksb36.contacts.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ksb36.contacts.model.Contact;
import com.example.ksb36.contacts.network.ContactsRepository;
import java.util.List;

public class ContactsViewModel extends ViewModel {


    private LiveData<List<Contact>> contactList;

    private MutableLiveData<Integer> selectedContact = new MutableLiveData<Integer>();


    public LiveData<Integer> getSelectedContact() {

        return selectedContact;
    }

    public void setSelectedContact(int position) {

        selectedContact.setValue(position);
    }

    public LiveData<List<Contact>> getContactList() {

        if (contactList == null) {
            contactList = ContactsRepository.getInstance().getContactlist();
        }

        return contactList;
    }

    public LiveData<ContactsRepository.NetworkStatus> getNetworkStatus() {
        return ContactsRepository.getInstance().getNetworkStatus();
    }
}
