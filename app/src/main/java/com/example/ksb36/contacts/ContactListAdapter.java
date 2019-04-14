package com.example.ksb36.contacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>{

    private List<Contact> contacts = new ArrayList<>();
    private View.OnClickListener itemClickListener;

    public ContactListAdapter(View.OnClickListener clickListener) {
        super();
        this.itemClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        if (contacts.isEmpty()) {
            System.out.print("NO DATA");
        }

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contact contact = contacts.get(i);

        viewHolder.setData(contact, i);

    }

    public void updateData(List<Contact> newData) {
                this.contacts = newData;
                this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;
        private TextView phoneNumber;
        private ImageView photo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = (TextView) itemView.findViewById(R.id.full_name);
            phoneNumber = (TextView) itemView.findViewById(R.id.phone_number);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public void setData(Contact contact, int position) {
            fullName.setText(contact.getFirstName() + " " + contact.getLastName());
            phoneNumber.setText(contact.getPhoneNumber());
            photo.setImageResource(contact.getImageResource());

            itemView.setTag(position);

            itemView.setOnClickListener(itemClickListener);
        }
    }
}
