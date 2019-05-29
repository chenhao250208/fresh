package com.store.fresh.service.Impl;

import com.store.fresh.entity.*;
import com.store.fresh.mapper.*;
import com.store.fresh.service.ShopCartService;
import com.store.fresh.service.UserService;
import com.store.fresh.util.DataUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    ShopCartMapper shopCartMapper;

    @Autowired
    UserService userService;

    @Autowired
    PicturePathMapper picturePathMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int addToCart(String productId) {
        String userId = userService.getUserIdFromSecurity();
        ShopCartExample shopCartExample = new ShopCartExample();
        ShopCartExample.Criteria criteria = shopCartExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andUserIdEqualTo(userId);
        List<ShopCart> shopCartList = shopCartMapper.selectByExample(shopCartExample);
        ShopCart shopCart = new ShopCart();
        if(shopCartList.size()==0){
            shopCart.setCount(1);
            shopCart.setUserId(userId);
            shopCart.setProductId(productId);
            shopCart.setAddTime(new Date());
            return shopCartMapper.insertSelective(shopCart);
        }else{
            ShopCart preShopCart = shopCartList.get(0);
            shopCart.setCount(preShopCart.getCount()+1);
            shopCart.setAddTime(new Date());
            return shopCartMapper.updateByExampleSelective(shopCart,shopCartExample);
        }
    }

    @Override
    public int updateCart(String productId, Integer count) {
        String userId = userService.getUserIdFromSecurity();

        ShopCartExample shopCartExample = new ShopCartExample();
        ShopCartExample.Criteria criteria = shopCartExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andProductIdEqualTo(productId);

        ShopCart shopCart = new ShopCart();
        shopCart.setCount(count);
        shopCart.setAddTime(new Date());
        return shopCartMapper.updateByExampleSelective(shopCart,shopCartExample);
    }

    @Override
    public List<ShopCart> getPersonalCarts() {
        String userId = userService.getUserIdFromSecurity();
        List<ShopCart> shopCartList = shopCartMapper.getCartsWithProductByUid(userId);
        for (ShopCart shopCart : shopCartList
             ) {
            PicturePathExample picturePathExample = new PicturePathExample();
            PicturePathExample.Criteria criteria = picturePathExample.createCriteria();
            criteria.andProductIdEqualTo(shopCart.getProductId());
            List<PicturePathKey> picturePathKeyList= picturePathMapper.selectByExample(picturePathExample);
            if(picturePathKeyList.size()>0){
                shopCart.setPicturePath(picturePathKeyList.get(0).getPicturePath());
            }
        }
        return shopCartList;
    }

    @Override
    @Transactional
    public void settle(List<Order> orderList) {
        String userId = userService.getUserIdFromSecurity();
        User user = userMapper.selectByPrimaryKey(userId);
        String orderId = DataUtil.getRandomNo(15);
        for (Order order:orderList
             ) {
            ShopCartExample shopCartExample = new ShopCartExample();
            ShopCartExample.Criteria criteria = shopCartExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andProductIdEqualTo(order.getProductId());
            shopCartMapper.deleteByExample(shopCartExample);

            Product product = productMapper.selectByPrimaryKey(order.getProductId());
            order.setOrderId(orderId);
            order.setPrice(product.getPrice()*order.getNumber());
            order.setOrderTime(new Date());
            order.setSaId(user.getShippingAddress());
            order.setState("待发货");
            order.setUserId(userId);
            orderMapper.insertSelective(order);
        }
    }
}
