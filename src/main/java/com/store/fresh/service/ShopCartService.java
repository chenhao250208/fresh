package com.store.fresh.service;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.ShopCart;

import java.util.List;

public interface ShopCartService {
    public int addToCart(String productId);
    public int updateCart(String productId,Integer count);
    public List<ShopCart> getPersonalCarts();
    public void settle(List<Order> orderList);
}
