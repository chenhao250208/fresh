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
        for(int i=0;i<productList.size();i++){
            String productId = productList.get(i).getProductId();
            List<String> picturePath = productMapper.getPicturePath(productId);
            productList.get(i).setPicturePath(picturePath);
        }
        return productList;
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.findAll();
    }

    @Override
    public Product selectByPrimaryKey(String productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public int insert(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int updateByPrimaryKey(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public int deleteByPrimaryKey(String productId) {
        return productMapper.deleteByPrimaryKey(productId);
    }
}
