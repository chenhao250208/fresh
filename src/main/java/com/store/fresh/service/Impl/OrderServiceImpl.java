package com.store.fresh.service.Impl;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.OrderExample;
import com.store.fresh.mapper.OrderMapper;
import com.store.fresh.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

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
