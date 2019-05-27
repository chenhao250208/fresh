package com.store.fresh.controller.foreground;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductsController {

/*    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/foreground/products")
    public String foregroundIndex(){
        return "foreground/products";
    }

    @RequiresRoles({"admin"})
    @GetMapping("/background/index")
    public String backgroundIndex(){
        return "background/index";
    }*/
}
