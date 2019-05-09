package com.store.fresh.entity;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Order {
    private Integer orderId;
    private String productId;
    private Date orderTime;
    private String userId;
    private Float price;
    private Integer number;
    private String state;
    private List<String> orderList;
}
