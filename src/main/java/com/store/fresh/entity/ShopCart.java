package com.store.fresh.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCart {
    private String userId;

    private String productId;

    private Integer count;

    private Date addTime;

    private String picturePath;

    private String productName;

    private Float price;
}