package com.example.ksb36.contacts.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksb36.contacts.model.Article;
import com.example.ksb36.contacts.R;
import com.example.ksb36.contacts.model.ArticleList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder>{

    private List<Article> articlesList = new ArrayList<>();
    private View.OnClickListener itemClickListener;

    public ArticleListAdapter(View.OnClickListener clickListener) {
        super();
        this.itemClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article article = articlesList.get(i);

        viewHolder.setData(article, i);

    }

    public void updateData(List<Article> newData) {
                this.articlesList = newData;
                this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private ImageView photo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public void setData(Article article, int position) {
            title.setText(article.getTitle());
            author.setText(article.getAuthor());

            Picasso.get()
                    .load(article.getImageURL())
                    .resize(50,50)
                    .centerCrop()
                    .into(photo);

            itemView.setTag(position);

            itemView.setOnClickListener(itemClickListener);
        }
    }
}
