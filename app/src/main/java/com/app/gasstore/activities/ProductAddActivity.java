package com.app.gasstore.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.app.gasstore.R;
import com.app.gasstore.databinding.ActivityProductAddBinding;
import com.app.gasstore.databinding.ActivityProductViewBinding;
import com.app.gasstore.models.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductAddActivity extends BaseActivity {
    DatabaseReference databaseReference ;
    ActivityProductAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProductAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        addEvent();
    }



    private void addEvent() {
//        binding.btnSaveProduct.setOnClickListener(v -> {
//            String name = binding.edtProductName.getText().toString();
//            String price = binding.etProductPrice.getText().toString();
//            String quantity = binding.etProductQuantity.getText().toString();
//            String description = binding.etProductDescription.getText().toString();
//            String color = binding.edtProductColor.getText().toString();
//            String mass = binding.edtProductMass.getText().toString();
//            String origin = binding.edtProductOrigin.getText().toString();
//            Products p = new Products("defauft",
//                    false,
//                    name,
//                    name,
//                    Double.parseDouble(price),
//                    "defauft",
//                    Integer.parseInt(quantity),
//                    description,
//                    color,
//                    "defauft",
//                    mass,
//                    origin,
//                    "defauft",
//                    "defauft",
//                    0,
//                    0,
//                    0);
//            database.child("Products2").push().setValue(p)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Log.d("Firebase", "Data written successfully!");
//                        } else {
//                            Log.e("Firebase", "Failed to write data.", task.getException());
//                        }
//                    });
//        });
        binding.btnSaveProduct.setOnClickListener(v -> {
            String name = binding.edtProductName.getText().toString().trim();
            String price = binding.edtProductPrice.getText().toString().trim();
            String quantity = binding.etProductQuantity.getText().toString().trim();
            String description = binding.edtProductDescription.getText().toString().trim();
            String color = binding.edtProductColor.getText().toString().trim();
            String mass = binding.edtProductMass.getText().toString().trim();
            String origin = binding.edtProductOrigin.getText().toString().trim();

            if (name.isEmpty() || price.isEmpty() || quantity.isEmpty() || description.isEmpty() ||
                    color.isEmpty() || mass.isEmpty() || origin.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double priceValue = Double.parseDouble(price);
                int quantityValue = Integer.parseInt(quantity);

                Products p = new Products(
                        "default", false, name, name, priceValue, "default",
                        quantityValue, description, color, "default", mass, origin,
                        "default", "default", 0, 0, 0
                );

                databaseReference.child("Products").push().setValue(p)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                                Log.d("Firebase", "Data written successfully!");
                                finish();
                            } else {
                                Toast.makeText(this, "Failed to add product.", Toast.LENGTH_SHORT).show();
                                Log.e("Firebase", "Failed to write data.", task.getException());
                            }
                        });

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number format!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

//    private void sendOrderToFirebase() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        DecimalFormat formatter = new DecimalFormat("#,###");
//        String formattedTime = sdf.format(new Date(System.currentTimeMillis()));
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(R.layout.dialog_loading);
//        builder.setCancelable(false);
//        AlertDialog progressDialog = builder.create();
//        progressDialog.show();
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Map<String, Object> orderData = new HashMap<>();
//
//        // Tạo danh sách các món trong giỏ hàng với chỉ title, price và quantity
//        ArrayList<Map<String, Object>> cartList = new ArrayList<>();
//        for (CProduct product : managmentProduct.getListCart()) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("title", product.getName());
//            item.put("giá", product.getPrice());
//            item.put("Số lượng", product.getQuantity());
//            cartList.add(item);
//        }
//
//        double percentTax = .02;
//        double delivery = 15000;
//        double total = Math.round((managmentCart.getTotalFee() + tax + delivery - discount) * 100) / 100;
//
//        // Thêm thông tin giỏ hàng vào dữ liệu đơn hàng
//        orderData.put("items", cartList);
//        orderData.put("Thành tiền", formatter.format(total) + "đ");
//        orderData.put("Thời gian đặt hàng", formattedTime);
//        orderData.put("Thuế", formatter.format(tax) + "đ");
//        orderData.put("Giảm giá", formatter.format(discount) + "đ");
//
//        // Gửi dữ liệu lên Firebase
//        db.collection("Orders")
//                .add(orderData)
//                .addOnSuccessListener(documentReference -> {
//                    progressDialog.dismiss();
//                    managmentCart.clearCart(); // Làm trống giỏ hàng
//                    showSuccessDialog();
//                })
//                .addOnFailureListener(e -> {
//                    progressDialog.dismiss();
//                    Toast.makeText(this, "Lỗi khi gửi đơn hàng: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
}