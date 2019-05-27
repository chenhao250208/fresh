package com.store.fresh.controller.background;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import com.store.fresh.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/background/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping({"/products", "/"})
    @ResponseBody
    public List<Product> selectAllProducts() {
        return productService.selectAll();
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public Product selectProductByPrimaryKey(@PathVariable(value = "productId", required = true) String productId) {
        return productService.selectByPrimaryKey(productId);
    }

    @PostMapping("/insert")
    @ResponseBody
    public Product insertProduct(@RequestParam("newProduct") Product newProduct) {
        newProduct.setProductId(DataUtil.getRandomNo(10));
        productService.insert(newProduct);
        return newProduct;
    }

    @PutMapping("/{productId}")
    @ResponseBody
    public Product updateProduct(
            @PathVariable(value = "productId", required = true) String productId,
            @RequestParam("newProduct") Product newProduct
    )
    {
        Product product = productService.selectByPrimaryKey(productId);
        if(null == product) {
            productService.insert(newProduct);
        } else {
            productService.updateByPrimaryKey(newProduct);
        }
        return newProduct;
    }

    @DeleteMapping("/delete/{productId}")
    @ResponseBody
    public Map<String, Object> deleteProductByPrimaryKey(@PathVariable("productId") String productId) {
        HashMap<String, Object> res = new HashMap<>();
        if(0 != productService.deleteByPrimaryKey(productId)) {
            res.put("code", 200);
            res.put("msg", "删除成功");
        } else {
            res.put("code", 500);
            res.put("msg", "删除失败");
        }
        return res;
    }

}
