package com.app.gasstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.gasstore.R;
import com.app.gasstore.databinding.ActivityHomeBinding;
import com.app.gasstore.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends BaseActivity {
    private GoogleSignInClient mGoogleSignInClient;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        addEvents();
    }

    private void addEvents() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });
    }

    public void signout() {
        // Đăng xuất người dùng khỏi Firebase
        mAuth.signOut();

        // Đăng xuất người dùng khỏi Google Sign-In
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            // Sau khi đăng xuất thành công, cập nhật UI và chuyển hướng về LoginActivity
            updateUI(null);

            // Điều hướng về màn hình đăng nhập (LoginActivity)
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();  // Đảm bảo đóng Activity hiện tại
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

            Toast.makeText(this, "Welcome, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }
}