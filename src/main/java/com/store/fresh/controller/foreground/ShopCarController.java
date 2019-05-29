package com.store.fresh.controller.foreground;

import com.store.fresh.entity.ShopCart;
import com.store.fresh.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("foreground/shopcart")
public class ShopCarController {
    @Autowired
    ShopCartService shopCartService;
    @GetMapping("/index")
    public String index(Model model){
        List<ShopCart> shopCartList = shopCartService.getPersonalCarts();
        model.addAttribute("shopCartList",shopCartList);
        return "foreground/shopcart/index";
    }
}
