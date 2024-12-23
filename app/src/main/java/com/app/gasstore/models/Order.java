package com.app.gasstore.models;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private String idUser;
    private List<Products> products;
    private double totalPrice;
    private Date dateCreated;
    private Date dateUpdated;
}
