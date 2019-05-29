package com.store.fresh.api.foreground;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.ShopCart;
import com.store.fresh.mapper.ShopCartMapper;
import com.store.fresh.service.ShopCartService;
import com.store.fresh.util.ResponseEntity;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shopcart")
public class ShopCartApi {
    @Autowired
    ShopCartService shopCartService;

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity add(String productId){
        if(shopCartService.addToCart(productId)==1){
            return ResponseEntity.ok("添加到购物车成功");
        }else{
            return ResponseEntity.error(400,"未知错误");
        }
    }

    @PostMapping("/update")
    public @ResponseBody
    ResponseEntity update(String productId,Integer count){
        if(shopCartService.updateCart(productId,count)==1){
            return ResponseEntity.ok("修改购物车成功");
        }else{
            return ResponseEntity.error(400,"错误");
        }
    }

    @PostMapping("/settle")
    public @ResponseBody
    ResponseEntity settle(@RequestBody List<Order> orderList){
        for (Order order: orderList
             ) {
            shopCartService.settle(orderList);
        }
        return ResponseEntity.ok("购物成功");
    }
}
