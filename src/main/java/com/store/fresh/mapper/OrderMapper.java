package com.store.fresh.mapper;

import com.store.fresh.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    List<Order> selectAll();
}