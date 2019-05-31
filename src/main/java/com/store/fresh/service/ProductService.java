package com.store.fresh.service;

import com.store.fresh.entity.Product;
import com.store.fresh.entity.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    public List<String> getPicturePathById(String productId);
    public List<Product> getHotDiscount();

    List<Product> selectAll();

    Product selectByPrimaryKey(String productId);

    int insert(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(String productId);

    List<Product> selectByCategory(String categoty);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String productId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<String> getPicturePathById(String productId);

    List<Product> getHotDiscount();

}
