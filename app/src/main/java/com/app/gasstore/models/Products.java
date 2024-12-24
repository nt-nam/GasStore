package com.app.gasstore.models;


import java.io.Serializable;

public class Products implements Serializable {
    private String id;//mã
    private boolean BestProduct;//Sản phẩm bán chạy
    private String name;//tên
    private String Title;//tên
    private double Price;//giá
    private String ImagePath;

    private int quantity;//số lượng
    private String description;//mô tả
    private String color;//màu
    private String size; //kích thước
    private int mass; //khối lượng
    private String Origin;//xuất xứ
    private String supplier;//nhà cung cấp
    private String status;//trạng thái sản phẩm
    private double Star;//trạng thái sản phẩm

    private int view;//lượt xem
    private int quantitySold;//số lượng đã bán

    public Products() {
    }

    public double getStar() {
        return Star;
    }

    public boolean isBestProduct() {
        return BestProduct;
    }

    public void setBestProduct(boolean bestProduct) {
        BestProduct = bestProduct;
    }

    public void setStar(double star) {
        Star = star;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Products(String id, String name, String title, double price, String imagePath, int quantity, String description, String color, String size, int mass, String origin, String supplier, String status, double star, int view, int quantitySold) {
        this.id = id;
        this.name = name;
        Title = title;
        Price = price;
        ImagePath = imagePath;
        this.quantity = quantity;
        this.description = description;
        this.color = color;
        this.size = size;
        this.mass = mass;
        Origin = origin;
        this.supplier = supplier;
        this.status = status;
        Star = star;
        this.view = view;
        this.quantitySold = quantitySold;
    }

    public Products(String id, boolean bestProduct, String name, String title, double price, String imagePath, int quantity, String description, String color, String size, int mass, String origin, String supplier, String status, double star, int view, int quantitySold) {
        this.id = id;
        BestProduct = bestProduct;
        this.name = name;
        Title = title;
        Price = price;
        ImagePath = imagePath;
        this.quantity = quantity;
        this.description = description;
        this.color = color;
        this.size = size;
        this.mass = mass;
        Origin = origin;
        this.supplier = supplier;
        this.status = status;
        Star = star;
        this.view = view;
        this.quantitySold = quantitySold;
    }

    @Override
    public String toString() {
        return name;
    }
}
