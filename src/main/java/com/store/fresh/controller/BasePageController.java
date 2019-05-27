package com.store.fresh.controller;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class BasePageController {
    @Autowired
    ProductService productService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/foreground/index")
    public String foregroundIndex(Model model){
        List<Product> discountList = productService.getHotDiscount();
        model.addAttribute("discountList",discountList);
        return "foreground/index";
    }

    @RequiresRoles({"admin"})
    @GetMapping("/background/index")
    public String backgroundIndex(){
        return "background/index";
    }
}
