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
import com.store.fresh.entity.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.Date;

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
        for (Order order : orderList
                ) {
            Product product = productMapper.selectByPrimaryKey(order.getProductId());
            order.setOrderId(orderId);
            order.setPrice(product.getPrice() * order.getNumber());
            order.setOrderTime(new Date());
            order.setSaId(user.getShippingAddress());
            order.setState("待发货");
            order.setUserId(userId);
            orderMapper.insertSelective(order);
        }
        return true;
    }
    public long countByExample(OrderExample example) {
        return orderMapper.countByExample(example);
    }

    public int deleteByExample(OrderExample example) {
        return orderMapper.deleteByExample(example);
    }

    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    public List<Order> selectByExample(OrderExample example) {
        return orderMapper.selectByExample(example);
    }

    public int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example) {
        return orderMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") Order record, @Param("example") OrderExample example) {
        return orderMapper.updateByExample(record, example);
    }

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public Order selectOrderByPrimaryKey(String orderId) {
        return orderMapper.selectOrderByPrimaryKey(orderId);
    }

}
