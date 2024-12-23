package com.app.gasstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.R;
import com.app.gasstore.activities.BaseActivity;
import com.app.gasstore.models.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.viewholder> {
    ArrayList<Product> items;
    Context context;

    public ProductListAdapter(ArrayList<Product> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.viewholder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        holder.titleTxt.setText(items.get(position).getName());
        holder.priceTxt.setText("₫"+formatter.format(items.get(position).getPrice()));
        holder.timeTxt.setText(items.get(position).getView()+" phút");
        holder.starTxt.setText(""+items.get(position).getId());

//        Glide.with(context)
//                .load(items.get(position).getImagePath())
//                .transform(new CenterCrop() , new RoundedCorners(30))
//                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent =new Intent(context, BaseActivity.class);
            intent.putExtra("object" , (CharSequence) items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt ,priceTxt,timeTxt,starTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleText);
            priceTxt=itemView.findViewById(R.id.priceTxt);
            timeTxt=itemView.findViewById(R.id.timeTxt);
            starTxt=itemView.findViewById(R.id.rateTxt);
            pic=itemView.findViewById(R.id.pic);


        }
    }
}
