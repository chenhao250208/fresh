package com.store.fresh.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foreground/order")
public class OrderForeController {

    @GetMapping("/myOrder")
    public String myOrder(){
        return "foreground/order/myOrder";
    }
}
