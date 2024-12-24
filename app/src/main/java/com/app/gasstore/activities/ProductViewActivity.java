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
    private int optionsShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getIntentExtra();
        initList();
        addEvents();
    }

    private void initList() {
        if (optionsShow == -1 ) {
            // Ẩn TextView
//            binding.textView14.setVisibility(View.GONE);
            // Hiện TextView
            binding.textView14.setVisibility(View.VISIBLE);
            initFullList();
            // Ẩn nhưng vẫn giữ không gian
//            binding.textView14.setVisibility(View.INVISIBLE);
        } else if (searchText.isEmpty()) {
            initFullList();
        } else if (optionsShow == 2) {
            //search
            // ẩn TextView dấu plus
//            binding.textView14.setVisibility(View.GONE);
            searchListProduct();
        }

    }

    private void searchListProduct() {
        DatabaseReference myRef = database.getReference("Products");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Products> list = new ArrayList<>();
        ArrayList<Products> fullList = new ArrayList<>(); // Danh sách đầy đủ
        ArrayList<Products> filteredList = new ArrayList<>(); // Danh sách sau khi lọc

//        Query query;
//
//        query = myRef.orderByChild("name")
//                .startAt(searchText)
//                .endAt(searchText + '\uf8ff');
//
//
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot issue : snapshot.getChildren()) {
//                        list.add(issue.getValue(Products.class));
//                    }
//                    if (list.size() > 0) {
//                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 2, RecyclerView.VERTICAL, false));
//                        adapterListProduct = new ProductListAdapter(list);
//                        binding.foodList.setAdapter(adapterListProduct);
//                    }
//                    binding.progressBar.setVisibility(View.GONE);
//                } else {
//                    binding.progressBar.setVisibility(View.GONE);
//                    Toast.makeText(ProductViewActivity.this, "Không tìm thấy kết quả!", Toast.LENGTH_SHORT).show();
//                }
//            }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        fullList.add(issue.getValue(Products.class));
                    }

                    // Thực hiện tìm kiếm không phân biệt chữ hoa/chữ thường
                    String lowerCaseSearchText = searchText.toLowerCase();
                    for (Products product : fullList) {
                        if (product.getName() != null && product.getName().toLowerCase().contains(lowerCaseSearchText)) {
                            filteredList.add(product);
                        }
                    }

                    // Hiển thị kết quả
                    if (filteredList.size() > 0) {
                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 2, RecyclerView.VERTICAL, false));
                        adapterListProduct = new ProductListAdapter(filteredList);
                        binding.foodList.setAdapter(adapterListProduct);
                    } else {
                        Toast.makeText(ProductViewActivity.this, "Không tìm thấy kết quả!", Toast.LENGTH_SHORT).show();
                    }

                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProductViewActivity.this, "Không có dữ liệu!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ProductViewActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

    private void initFullList() {
        DatabaseReference myRef = database.getReference("Products");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Products> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Products.class));
                    }
                    if (list.size() > 0) {
                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 2, RecyclerView.VERTICAL, false));
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
        searchText = "";
        searchText = getIntent().getStringExtra("searchText");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();

    }
}