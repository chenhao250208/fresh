package com.store.fresh.service;

import com.store.fresh.entity.Product;

import java.util.List;

public interface ProductService {
    public List<String> getPicturePathById(String productId);
    public List<Product> getHotDiscount();

    List<Product> selectAll();

    Product selectByPrimaryKey(String productId);

    int insert(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(String productId);
}
