package com.store.fresh.service.Impl;

import com.store.fresh.entity.Product;
import com.store.fresh.entity.ProductExample;
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
    public List<Product> getOnSaleProductList() {
        List<Product> productList = productMapper.getOnSaleProductList();
/*        for(int i=0; i<productList.size(); i++) {
            String productId = productList.get(i).getProductId();
            List<String> picturePath = productMapper.getPicturePath(productId);
            productList.get(i).setPicturePath(picturePath);
        }*/
        return productList;
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.findAll();
    }

    @Override
    public Product selectByPrimaryKey(String productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        product.setPicturePath(getPicturePathById(productId));
        return product;
    }

    @Override
    public int insert(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Override
    public List<Product> selectByExample(ProductExample example) {
        return productMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }


    @Override
    public int deleteByPrimaryKey(String productId) {
        return productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public List<Product> selectByCategory(String categoty) {
        ProductExample productExample = new ProductExample();
        if(!categoty.equals("all")){
            ProductExample.Criteria criteria = productExample.createCriteria();
            criteria.andCategoryEqualTo(categoty);
        }
        List<Product> productList = productMapper.selectByExample(productExample);
        for (Product product: productList
             ) {
            List<String> picturePaths = getPicturePathById(product.getProductId());
            product.setPicturePath(picturePaths);
        }
        return productList;
    }

    @Override
    public int updateByExampleSelective(Product record, ProductExample example) {
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Product record, ProductExample example) {
        return productMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }
}
