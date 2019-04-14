package com.example.ksb36.contacts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsViewModel extends ViewModel {

    private MutableLiveData<Integer> selectedContact = new MutableLiveData<Integer>();

    public LiveData<Integer> getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(int position) {
        selectedContact.setValue(position);
    }

    MutableLiveData<List<Contact>> contactList = new MutableLiveData<List<Contact>>();
    public LiveData<List<Contact>> getContactList() {
        return contactList;
    }



    public void loadContacts() {
        ArrayList<Contact> newData = new ArrayList<Contact>();
        this.contactList.setValue(newData);

        newData.add(new Contact("John", "Snow", "+44 123456 789", R.drawable.jonsnow));
        newData.add(new Contact("Arya", "Name", "13524675735", R.drawable.arya));
        newData.add(new Contact("Cersei", "Name", "927542596986", R.drawable.cersei));
        newData.add(new Contact("Daenerys", "Name", "367388347384", R.drawable.daenerys));
        newData.add(new Contact("Sansa", "Name", "65743463747", R.drawable.sansa));
        newData.add(new Contact("Tyrion", "Name", "564837838", R.drawable.tyrion));
    }

}
