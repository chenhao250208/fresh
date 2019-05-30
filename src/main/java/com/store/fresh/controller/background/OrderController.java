package com.store.fresh.controller.background;

import com.store.fresh.entity.Order;
import com.store.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("background/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping({"/index", ""})
    public String index(){
        return "background/order/index";
    }

    @GetMapping("/edit/{orderId}")
    public String edit(Model model, @PathVariable("orderId")String orderId){
        Order order = orderService.selectOrderByPrimaryKey(orderId);
        model.addAttribute("order", order);
        return "background/order/edit";
    }

    @GetMapping("/pending")
    public String pending(){
        return "background/order/pending";
    }

    @GetMapping("/processing")
    public String processing(){
        return "background/order/processing";
    }

    @GetMapping("/solved")
    public String create(){
        return "background/order/solved";
    }

}
