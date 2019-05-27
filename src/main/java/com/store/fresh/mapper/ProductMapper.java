package com.store.fresh.mapper;

import com.store.fresh.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> selectAll();

    Product selectByPrimaryKey(String productId);

    int insert(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(String productId);
}