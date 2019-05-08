package com.store.fresh.entity;

import lombok.Data;

@Data
public class Product {
    private String productId;
    private String productName;
    private String category;
    private float cost;
    private float price;
    private int deposit;
    private String uint;
    private String scale;
}