package com.store.fresh.controller.background;

import com.store.fresh.entity.Order;
import com.store.fresh.entity.Product;
import com.store.fresh.entity.User;
import com.store.fresh.mapper.ProductMapper;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("background/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{state}")
    public String index(@PathVariable("state") String state, Model model){
        model.addAttribute("state", null==state? "all":state);
        return "background/order/index";
    }

    @GetMapping("/edit/{userId}/{orderId}/{productId}")
    public String edit(Model model,
                       @PathVariable("userId") String userId,
                       @PathVariable("orderId") String orderId,
                       @PathVariable("productId") String productId)
    {
        System.out.println(userId+orderId+productId);
        Order order = orderService.selectOrderByPrimaryKey(orderId, productId, userId);
        Product product = productMapper.selectByPrimaryKey(productId);
        User user = userMapper.selectByPrimaryKey(userId);
        model.addAttribute("order", order);
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "background/order/edit";
    }

}
