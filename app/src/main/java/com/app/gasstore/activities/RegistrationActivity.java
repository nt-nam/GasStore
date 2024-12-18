package com.app.gasstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.gasstore.R;
import com.app.gasstore.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends BaseActivity {
    ActivityRegistrationBinding binding;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setVariable();
    }
    private void setVariable() {
        binding.btnSignUp.setOnClickListener(view -> {
            String email = binding.edtinputUser.getText().toString().trim();
            String password = binding.edtinputPass.getText().toString().trim();
            String confirmPassword = binding.edtinputPass2.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "Đăng ký thành công!");
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Log.e(TAG, "Đăng ký thất bại: " + task.getException());
                            Toast.makeText(this, "Đăng ký thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Xử lý chuyển về trang đăng nhập
        binding.tvLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}