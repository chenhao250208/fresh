package com.store.fresh.service.Impl;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.Product;
import com.store.fresh.entity.User;
import com.store.fresh.mapper.OrderMapper;
import com.store.fresh.mapper.ProductMapper;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.service.OrderService;
import com.store.fresh.service.UserService;
import com.store.fresh.util.Base;
import com.store.fresh.util.DataUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.store.fresh.entity.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

import java.util.Date;
import java.util.Map;

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

    @Override
    @Transactional
    public boolean processOrder(String orderId, String productId, String userId) {
        Order order = new Order();
        order.setState("运输中");
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andProductIdEqualTo(productId);
        criteria.andUserIdEqualTo(userId);
        if(orderMapper.updateByExampleSelective(order, example) == 1) {
            return true;
        }
        return false;
    }

    public long countByExample(OrderExample example) {
        return orderMapper.countByExample(example);
    }

    public int deleteByExample(Order order, OrderExample example) {
        orderMapper.deleteByExample(example);
        Product product = new Product();
        product.setProductId(order.getProductId());
        product.setDeposit(product.getDeposit() + order.getNumber());
        return productMapper.updateByPrimaryKeySelective(product);
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
    public Order selectOrderByPrimaryKey(String orderId, String productId, String userId) {
        Map<String,String> info = new HashMap<>();
        info.put("userId", userId);
        info.put("orderId", orderId);
        info.put("productId", productId);
        return orderMapper.selectOrderByPrimaryKey(info);
    }

    @Override
    public List<Order> listByState(String state) {
        String userId = userService.getUserIdFromSecurity();
        User user = userMapper.selectByPrimaryKey(userId);
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("order_time desc");
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        if(Base.notEmpty(state)&& !state.equals("all")){
            criteria.andStateEqualTo(state);
        }
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        for (Order order:orderList
             ) {
            Product product = productMapper.selectByPrimaryKey(order.getProductId());
            List<String> picturePath = productMapper.getPicturePath(order.getProductId());
            if(picturePath.size()==0){
                picturePath.add("/images/defalt.jpg");
            }
            product.setPicturePath(picturePath);
            order.setUser(user);
            order.setProduct(product);
        }
        return orderList;
    }

}
