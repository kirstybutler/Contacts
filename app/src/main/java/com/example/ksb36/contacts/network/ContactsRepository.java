package com.example.ksb36.contacts.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.ksb36.contacts.model.Contact;
import com.example.ksb36.contacts.ui.ContactListFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsRepository {
    private Retrofit retrofit;
    private ContactsService contactsService;
    private ContactListFragment contactListFragment;

    private static final ContactsRepository ourInstance = new ContactsRepository();

    public enum NetworkStatus {
        IDLE, LOADING, SUCCESS, ERROR
    }
    public MutableLiveData<NetworkStatus> networkStatus = new MutableLiveData<>();

    public static ContactsRepository getInstance() {

        return ourInstance;
    }

    private ContactsRepository() {

        networkStatus.setValue(NetworkStatus.IDLE);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ContactsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        contactsService = retrofit.create(ContactsService.class);
    }

    public LiveData<List<Contact>> getContactlist() {
        final MutableLiveData<List<Contact>> data = new MutableLiveData<>();

        Call<List<Contact>> call = contactsService.getAllContacts();

        networkStatus.setValue(NetworkStatus.LOADING);

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                data.setValue(response.body());

                networkStatus.setValue(NetworkStatus.IDLE);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                networkStatus.setValue(NetworkStatus.IDLE);
            }
        });

        return data;
    }

    public LiveData<NetworkStatus> getNetworkStatus() {
        return networkStatus;
    }


}
