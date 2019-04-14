package com.example.ksb36.contacts.network;

import com.example.ksb36.contacts.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsService {

    static final String BASE_URL = "http://www.efstratiou.info/projects/contacts/";

    @GET("contacts.php")
    Call<List<Contact>> getAllContacts();
}
