package com.store.fresh.controller.foreground;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foreground/product")

public class ProductController {
    @GetMapping("/index")
    public String productIndex(){
        return "foreground/product/index"; }
}
