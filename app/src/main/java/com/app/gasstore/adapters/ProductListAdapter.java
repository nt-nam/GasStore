package com.app.gasstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.R;
import com.app.gasstore.activities.BaseActivity;
import com.app.gasstore.activities.ProductDetailActivity;
import com.app.gasstore.models.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.viewholder> {
    ArrayList<Products> items;
    Context context;

    public ProductListAdapter(ArrayList<Products> items) {
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
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText("₫"+formatter.format(items.get(position).getPrice()));
        holder.quantityTxt.setText(items.get(position).getQuantity()+" tồn kho");
        holder.starTxt.setText(""+items.get(position).getStar());

//        Glide.with(context)
//                .load(items.get(position).getImagePath())
//                .transform(new CenterCrop() , new RoundedCorners(30))
//                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent =new Intent(context, ProductDetailActivity.class);
            intent.putExtra("object" , items.get(position));
            context.startActivity(intent);
        });

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("gasdandung/gas-xam-petrovietnam-12kg.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            holder.pic.setImageBitmap(bitmap);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
//            Toast.makeText(this, "Không thể tải ảnh!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt ,priceTxt,quantityTxt,starTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleText);
            priceTxt=itemView.findViewById(R.id.priceTxt);
            quantityTxt=itemView.findViewById(R.id.quantityTxt);
            starTxt=itemView.findViewById(R.id.rateTxt);
            pic=itemView.findViewById(R.id.pic);


        }
    }
    private void initImage() {

    }
}
