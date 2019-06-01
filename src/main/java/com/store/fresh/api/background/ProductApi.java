package com.store.fresh.api.background;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.fresh.entity.Product;
import com.store.fresh.entity.ProductExample;
import com.store.fresh.mapper.ProductMapper;
import com.store.fresh.service.ProductService;
import com.store.fresh.util.Base;
import com.store.fresh.util.DataUtil;
import com.store.fresh.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    ProductService productService;

    @GetMapping("/getList")
    public @ResponseBody
    ResponseEntity getList(Integer pageNum,Integer pageSize,String criteria) {
        ProductExample productExample = new ProductExample();
//        ProductExample.Criteria productCriteria = productExample.createCriteria();
        if(Base.notEmpty(criteria)) {
            productExample.setOrderByClause("deposit desc");
            productExample.or().andProductIdLike('%'+criteria+'%');
            productExample.or().andProductNameLike('%'+criteria+'%');
            productExample.or().andCategoryLike('%'+criteria+'%');
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productService.selectByExample(productExample);
        PageInfo pageInfo = new PageInfo(productList);
        return ResponseEntity.ok("获取成功").put("pageInfo", pageInfo);
    }

    @GetMapping("/getOnSale")
    public @ResponseBody
    ResponseEntity getOnSaleProductList() {
        List<Product> productList = productService.getOnSaleProductList();
        return ResponseEntity.ok("查询成功").put("productList", productList);
    }

    @GetMapping("/getOne")
    public @ResponseBody
    ResponseEntity getOne(String productId) {
        Product product = productService.selectByPrimaryKey(productId);
        if(null != product)
            return ResponseEntity.ok().put("data", product);
        else
            return ResponseEntity.error(400, "数据接口错误");
    }

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity save(Product product) {
        String productId = DataUtil.getRandomNo(15);
        product.setProductId(productId);
        if(productService.insert(product) == 1) {
            return ResponseEntity.ok("添加成功").put(product.getProductId(), product);
        } else {
            return ResponseEntity.error(400, "添加失败");
        }
    }

    @PostMapping("/edit")
    public @ResponseBody
    ResponseEntity edit(Product product) {
        System.out.println(product);
        if(productService.updateByPrimaryKeySelective(product) == 1) {
            return ResponseEntity.ok("修改成功").put(product.getProductId(), product);
        }
        return ResponseEntity.error(400, "修改失败");
    }

    @GetMapping("/delete")
    public @ResponseBody
    ResponseEntity delete(String productId) {
        if(productService.deleteByPrimaryKey(productId) == 1) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.error(400, "删除失败");
    }
}
