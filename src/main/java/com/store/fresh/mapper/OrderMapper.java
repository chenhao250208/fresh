package com.store.fresh.mapper;

import com.store.fresh.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order ")
    List<Order> selectAll();

}
