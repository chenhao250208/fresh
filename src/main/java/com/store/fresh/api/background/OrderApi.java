package com.store.fresh.api.background;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.fresh.entity.Order;
import com.store.fresh.entity.OrderExample;
import com.store.fresh.service.OrderService;
import com.store.fresh.util.Base;
import com.store.fresh.util.DataUtil;
import com.store.fresh.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    OrderService orderService;

    @GetMapping("/getList")
    public @ResponseBody
    ResponseEntity getList(Integer pageNum, Integer pageSize, String criteria) {
        OrderExample orderExample = new OrderExample();
        if(Base.notEmpty(criteria)) {
            orderExample.setOrderByClause("order_time desc");
            orderExample.or().andOrderIdLike('%'+criteria+'%');
            orderExample.or().andProductIdLike('%'+criteria+'%');
            orderExample.or().andUserIdLike('%'+criteria+'%');
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderService.selectByExample(orderExample);
        PageInfo pageInfo = new PageInfo(orderList);
        return ResponseEntity.ok("获取成功").put("pageInfo", pageInfo);
    }

    @PostMapping("/edit")
    public @ResponseBody
    ResponseEntity edit(Order order) {
        if(orderService.updateByExampleSelective(order, null) == 1) {
            return ResponseEntity.ok("修改成功").put(order.getOrderId(), order);
        }
        return ResponseEntity.error(400, "修改失败");
    }

    @GetMapping("/delete")
    public @ResponseBody
    ResponseEntity delete(String productId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderIdEqualTo(productId);
        if(orderService.deleteByExample(orderExample) == 1) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.error(400, "删除失败");
    }

}