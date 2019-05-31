package com.store.fresh.controller.foreground;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("foreground/product")
public class ForeProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{category}")
    public String category(@PathVariable("category")String category,Model model){
        List<Product> productList = productService.selectByCategory(category);
        model.addAttribute("productList",productList);
        return "foreground/product/index";
    }

    @GetMapping("/detail")
    public String detail(String productId,Model model){
        Product product = productService.selectByPrimaryKey(productId);
        model.addAttribute("product",product);
        return "foreground/product/details";
    }
}
