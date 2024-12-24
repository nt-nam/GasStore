package com.app.gasstore.activities;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.gasstore.R;
import com.app.gasstore.databinding.ActivityProductDetailBinding;
import com.app.gasstore.models.Products;

import java.io.IOException;
import java.io.InputStream;

public class ProductDetailActivity extends BaseActivity {
    ActivityProductDetailBinding binding;
    private String productId;
    private Products product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getIntentExtra();
        initProduct();
        addEvents();
    }

    private void addEvents() {
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });


    }

    private void initImage() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("gasdandung/gas-xam-petrovietnam-12kg.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            binding.productImage.setImageBitmap(bitmap);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thể tải ảnh!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initProduct() {
        initImage();
        int numMass = 0;
        numMass = product.getMass();
        if (numMass >= 8) {
            numMass -= 8;
            binding.tvMass70.setBackgroundResource(R.drawable.cat_2_background);
        }
        if (numMass >= 4) {
            numMass -= 4;
//            binding.tvMass45.setVisibility(View.GONE);
            binding.tvMass45.setBackgroundResource(R.drawable.cat_2_background);
        }
        if (numMass >= 2) {
            numMass -= 2;
//            binding.tvMass24.setVisibility(View.GONE);
            binding.tvMass24.setBackgroundResource(R.drawable.cat_2_background);
        }
        if (numMass == 1) {
//            binding.tvMass12.setVisibility(View.GONE);
            binding.tvMass12.setBackgroundResource(R.drawable.cat_2_background);
        }
        binding.productName.setText(product.getName());
        binding.productPrice.setText(product.getPrice() + "");
        binding.productRating.setText(product.getStar() + "");
        binding.descriptionContent.setText(product.getDescription());
        binding.productName.setText(product.getName());
    }

    private void getIntentExtra() {
        product = (Products) getIntent().getSerializableExtra("object");

    }
}