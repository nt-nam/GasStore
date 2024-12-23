package com.app.gasstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gasstore.R;
import com.app.gasstore.adapters.CategoryAdapter;
import com.app.gasstore.databinding.ActivityHomeBinding;
import com.app.gasstore.models.Category;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private GoogleSignInClient mGoogleSignInClient;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        initCategory();
        addEvents();
    }

    private void initCategory() {
        DatabaseReference myRef=database.getReference("Category");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        binding.categoryView.setLayoutManager(
                                new GridLayoutManager(HomeActivity.this ,4)
                        );
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.categoryView.setAdapter(adapter);
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addEvents() {
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
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