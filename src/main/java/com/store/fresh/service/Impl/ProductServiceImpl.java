package com.store.fresh.service.Impl;

import com.store.fresh.entity.Product;
import com.store.fresh.mapper.ProductMapper;
import com.store.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<String> getPicturePathById(String productId) {
        return productMapper.getPicturePath(productId);
    }

    @Override
    public List<Product> getHotDiscount() {
        List<Product> productList = productMapper.getHotDiscount();
        System.out.println(productList.toString());
        for(int i=0;i<productList.size();i++){
            String productId = productList.get(i).getProductId();
            List<String> picturePath = productMapper.getPicturePath(productId);
            productList.get(i).setPicturePath(picturePath);
        }
        return productList;
    }
}
