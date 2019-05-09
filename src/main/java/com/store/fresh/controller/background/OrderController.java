package com.store.fresh.controller.background;

import com.store.fresh.entity.User;
import com.store.fresh.entity.UserExample;
import com.store.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("background/order")
public class OrderController {
    @Autowired
    UserService orderService;


    @GetMapping("/index")
    public String index(){
        return "background/order/index";
    }

   /* @GetMapping("/edit/{userId}")
    public String edit(Model model, @PathVariable("userId")String userId){
        User user = orderService.selectByPrimaryKey(userId);
        model.addAttribute("user",user);
        return "background/user/edit";
    }*/

    /*@GetMapping("/create")
    public String create(){
        return "background/user/create";
    }*/
}
