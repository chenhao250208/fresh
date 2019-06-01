package com.store.fresh.controller.foreground;

import com.store.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foreground/order")
public class ForeOrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/myOrder")
    public String myOrder(){
        return "foreground/order/myOrder";
    }

    @GetMapping("/index")
    public String index(){
        return "foreground/order/index";
    }

    @GetMapping("/detail")
    public String detail(){
        return "foreground/order/detail";
    }

    @GetMapping("/index/{state}")
    public String state(@PathVariable("state")String state, Model model){
        model.addAttribute("orderList",orderService.listByState(state));
        return "foreground/order/index";
    }
}
