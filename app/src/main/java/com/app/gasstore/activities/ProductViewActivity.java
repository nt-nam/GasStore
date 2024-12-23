package com.app.gasstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.adapters.ProductListAdapter;
import com.app.gasstore.databinding.ActivityProductViewBinding;
import com.app.gasstore.models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductViewActivity extends BaseActivity {
    ActivityProductViewBinding binding;
    private RecyclerView.Adapter adapterListProduct;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    int optionsShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getIntentExtra();
        addControls();
        initList();
        addEvents();
    }

    private void addControls() {
        if (optionsShow == -1) {
            // Ẩn TextView
//            binding.textView14.setVisibility(View.GONE);
            // Hiện TextView
            binding.textView14.setVisibility(View.VISIBLE);
            // Ẩn nhưng vẫn giữ không gian
//            binding.textView14.setVisibility(View.INVISIBLE);
        }

    }

    private void addEvents() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductViewActivity.this, ProductAddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Products");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Products> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            // Chuyển ký tự đầu thành chữ hoa
            String normalizedSearchText = searchText.substring(0, 1).toUpperCase() + searchText.substring(1);
            query = myRef.orderByChild("Name").startAt(normalizedSearchText).endAt(normalizedSearchText + '\uf8ff');
        } else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Products.class));
                    }
                    if (list.size() > 0) {
                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 3, RecyclerView.VERTICAL, false));
//                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 1));

                        adapterListProduct = new ProductListAdapter(list);
                        binding.foodList.setAdapter(adapterListProduct);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProductViewActivity.this, "Không tìm thấy kết quả!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ProductViewActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("categoryId", 0);
        optionsShow = getIntent().getIntExtra("optionsShow", 0);
        categoryName = getIntent().getStringExtra("categoryName");
        searchText = getIntent().getStringExtra("searchText");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
        
    }
}