package com.store.fresh.api.background;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.fresh.entity.Order;
import com.store.fresh.entity.OrderExample;
import com.store.fresh.service.OrderService;
import com.store.fresh.util.Base;
import com.store.fresh.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getList")
    public @ResponseBody
    ResponseEntity getList(Integer pageNum,Integer pageSize,
                           String criteria, @RequestParam("state") String state) {
        OrderExample productExample = new OrderExample();
        if(Base.notEmpty(criteria)) {
            productExample.setOrderByClause("order_time desc");
            productExample.or().andProductIdLike('%'+criteria+'%');
            productExample.or().andOrderIdLike('%'+criteria+'%');
            productExample.or().andUserIdLike('%'+criteria+'%');
        }
        switch (state) {
            case "pending":
                productExample.or().andStateEqualTo("待发货");
                break;
            case "processing":
                productExample.or().andStateEqualTo("运输中");
                break;
            case "solved":
                productExample.or().andStateEqualTo("已完成");
                break;
            default:
                break;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderService.selectByExample(productExample);
        PageInfo pageInfo = new PageInfo(orderList);
        return ResponseEntity.ok("获取成功").put("pageInfo", pageInfo);
    }

    @PostMapping("/edit")
    public @ResponseBody
    ResponseEntity edit(Order order) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(order.getOrderId());
        criteria.andUserIdEqualTo(order.getUserId());
        criteria.andProductIdEqualTo(order.getProductId());

        if(orderService.updateByExampleSelective(order, example) == 1) {
            return ResponseEntity.ok("修改成功").put(order.getOrderId(), order);
        }

        return ResponseEntity.error(400, "修改失败");
    }

    @PostMapping("/process")
    public @ResponseBody
    ResponseEntity process(String orderId, String productId, String userId) {
        if(orderService.processOrder(orderId, productId, userId)) {
            return ResponseEntity.ok("已处理订单!");
        }
        return ResponseEntity.error(400, "数据接口错误");
    }

    @GetMapping("/delete")
    public @ResponseBody
    ResponseEntity delete(String orderId, String productId, String userId) {
        Order order = orderService.selectOrderByPrimaryKey(orderId, productId, userId);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderIdEqualTo(orderId);
        criteria.andProductIdEqualTo(productId);

        if(orderService.deleteByExample(order, orderExample) == 1) {
            return ResponseEntity.ok("订单删除成功");
        }

        return ResponseEntity.error(400, "订单删除失败");
    }
}
