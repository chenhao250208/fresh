package com.store.fresh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class BasePageController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/foreground/index")
    public String foregroundIndex(){
        return "foreground/index";
    }

    @RequiresRoles({"admin"})
    @GetMapping("/background/index")
    public String backgroundIndex(){
        return "background/index";
    }
}
