package com.store.fresh.service.Impl;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.Product;
import com.store.fresh.entity.User;
import com.store.fresh.mapper.OrderMapper;
import com.store.fresh.mapper.ProductMapper;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.service.OrderService;
import com.store.fresh.service.UserService;
import com.store.fresh.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    @Transactional
    public boolean saveOrderList(List<Order> orderList) {
        String userId = userService.getUserIdFromSecurity();
        User user = userMapper.selectByPrimaryKey(userId);
        String orderId = DataUtil.getRandomNo(15);
        for (Order order:orderList
             ) {
            Product product = productMapper.selectByPrimaryKey(order.getProductId());
            order.setOrderId(orderId);
            order.setPrice(product.getPrice()*order.getNumber());
            order.setOrderTime(new Date());
            order.setSaId(user.getShippingAddress());
            order.setState("待发货");
            order.setUserId(userId);
            orderMapper.insertSelective(order);
        }
        return true;
    }
}
