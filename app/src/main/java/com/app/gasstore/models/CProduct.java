package com.app.gasstore.models;

import java.util.Date;

public class CProduct {
    private String id;
    private String name;
    private double price;
    private int number;
    private String description;
    private String color;//màu
    private String size; //kích thước
    private String mass; //khối lượng
    private String Origin;//xuất xứ
    private String supplier;//nhà cung cấp
    private String expiry;//hạn sử dụng
    private String guarantee;//bảo hành
    private String status;//trạng thái sản phẩm
    private Date dateCreated;//ngày tạo
    private Date dateUpdated;//ngày cập nhật
    private int view;//lượt xem
    private int quantitySold;//số lượng đã bán
}
