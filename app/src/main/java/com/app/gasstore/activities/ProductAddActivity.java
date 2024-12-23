package com.app.gasstore.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.gasstore.utils.ManagmentProduct;

import com.app.gasstore.R;

public class ProductAddActivity extends BaseActivity {
    ManagmentProduct managmentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        managmentProduct = new ManagmentProduct(this);
        init();
    }

    private void init() {

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