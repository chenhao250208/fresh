package com.store.fresh.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private String orderId;

    private String productId;

    private Product product;

    private Date orderTime;

    private String userId;

    private User user;

    private String saId;

    private Integer number;

    private Float price;

    private String state;

}