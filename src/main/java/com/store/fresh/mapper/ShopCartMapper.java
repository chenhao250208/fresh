package com.store.fresh.mapper;

import com.store.fresh.entity.ShopCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCartMapper {
    int insert(ShopCart record);

    List<ShopCart> selectAll();
}