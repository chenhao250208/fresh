package com.store.fresh.controller.background;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/background/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "background/product/index";
    }

    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") String productId, Model model) {
        Product product = productService.selectByPrimaryKey(productId);
        model.addAttribute("product", product);
        return "background/product/edit";
    }

    @GetMapping("/create")
    public String save() {
        return "background/product/create";
    }

    @GetMapping("/onsale")
    public String onsale() {
        return "/background/product/onsale";
    }
}
