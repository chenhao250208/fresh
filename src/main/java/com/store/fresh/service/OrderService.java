package com.store.fresh.service;


import com.store.fresh.entity.Order;

import java.util.List;

public interface OrderService {
    public boolean saveOrderList(List<Order> orderList);
}
