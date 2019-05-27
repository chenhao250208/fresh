package com.store.fresh.entity;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String productId;
    private String productName;
    private String category;
    private Float cost;
    private Float prePrice;
    private Float price;
    private int deposit;
    private String unit;
    private String scale;
    private List<String> picturePath;
}