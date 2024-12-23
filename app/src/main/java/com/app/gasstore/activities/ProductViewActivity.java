package com.app.gasstore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.adapters.ProductListAdapter;
import com.app.gasstore.databinding.ActivityProductViewBinding;
import com.app.gasstore.models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductViewActivity extends BaseActivity {
    ActivityProductViewBinding binding;
    private  RecyclerView.Adapter adapterListProduct;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Product");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Product> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            // Chuyển ký tự đầu thành chữ hoa
            String normalizedSearchText = searchText.substring(0, 1).toUpperCase() + searchText.substring(1);
            query = myRef.orderByChild("Name").startAt(normalizedSearchText).endAt(normalizedSearchText + '\uf8ff');
        } else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Product.class));
                    }
                    if (list.size() > 0) {
                        binding.foodList.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 2));
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
        categoryId=getIntent().getIntExtra("categoryId",0);
        categoryName=getIntent().getStringExtra("categoryName");
        searchText=getIntent().getStringExtra("searchText");
        isSearch=getIntent().getBooleanExtra("isSearch",false);

    }
}