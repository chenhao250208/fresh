package com.store.fresh.api.foreground;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.User;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.service.OrderService;
import com.store.fresh.service.UserService;
import com.store.fresh.util.DataUtil;
import com.store.fresh.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class ForeOrderApi {
    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity save(@RequestBody List<Order> orderList){
        if(orderService.saveOrderList(orderList)){
            return ResponseEntity.ok("订单创建成功");
        }else{
            return ResponseEntity.error(400,"未知错误");
        }
    }
}
