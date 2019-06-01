package com.store.fresh.service;



import com.store.fresh.entity.Order;
import com.store.fresh.entity.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    public boolean saveOrderList(List<Order> orderList);

    public boolean processOrder(String orderId, String productId, String userId);

    long countByExample(OrderExample example);

    int deleteByExample(Order order, OrderExample example);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    List<Order> findAll();

    Order selectOrderByPrimaryKey(String orderId, String productId, String userId);
}
