package com.app.gasstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.R;
import com.app.gasstore.models.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {
    ArrayList<News> items;
    Context context;
    public NewsAdapter(ArrayList<News> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NewsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsAdapter.viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.viewHolder holder, int position) {
        holder.titleText.setText(items.get(position).getTitle());
        holder.dateCreatedTxt.setText(items.get(position).getDateCreated());
        holder.contentTxt.setText(items.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleText,dateCreatedTxt,contentTxt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleText);
            dateCreatedTxt=itemView.findViewById(R.id.dateCreatedTxt);
            contentTxt=itemView.findViewById(R.id.contentTxt);
        }
    }
}
