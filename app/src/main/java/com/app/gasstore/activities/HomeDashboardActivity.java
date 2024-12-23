package com.app.gasstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.gasstore.R;
import com.app.gasstore.databinding.ActivityHomeBinding;
import com.app.gasstore.databinding.ActivityHomeDashboardBinding;

public class HomeDashboardActivity extends AppCompatActivity {
    ActivityHomeDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addEvents();
    }

    private void addEvents() {
        binding.logoutBtn.setOnClickListener(v -> {
            finish();
        });
        binding.constraintLayoutSanPham.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProductViewActivity.class);
            intent.putExtra("optionsShow", -1);
            startActivity(intent);
        });
    }
}