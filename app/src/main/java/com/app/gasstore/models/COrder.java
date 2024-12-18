package com.app.gasstore.models;

import java.util.Date;
import java.util.List;

public class COrder {
    private String id;
    private String idUser;
    private List<CProduct> products;
    private double totalPrice;
    private Date dateCreated;
    private Date dateUpdated;
}
