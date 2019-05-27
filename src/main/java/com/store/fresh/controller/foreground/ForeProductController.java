package com.store.fresh.controller.foreground;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("foreground/product")
public class ForeProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/index")
    public String index(Model model){
        return "foreground/product/index";
    }
}
