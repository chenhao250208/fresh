package com.store.fresh.controller;

import com.store.fresh.entity.Product;
import com.store.fresh.service.ProductService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class BasePageController {
    @Autowired
    ProductService productService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String foregroundIndex(Model model){
        List<Product> discountList = productService.getHotDiscount();
        model.addAttribute("discountList",discountList);
        return "foreground/index";
    }

    @RequiresRoles({"admin"})
    @GetMapping("/background/index")
    public String backgroundIndex(){
        return "background/index";
    }

    @RequestMapping("/last_access")
    public void turnToLastAccess(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        String contenxPath = request.getContextPath();
        System.out.println(savedRequest.getRequestUrl());
        String fallBackUrl = savedRequest.getRequestUrl().replace(contenxPath,"");
        WebUtils.redirectToSavedRequest(request,response,fallBackUrl);
    }

    @PostMapping("login")
    public String login(String userName,String password,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("subject", subject);
            if(WebUtils.getSavedRequest(request)!=null){
                return "forward:/last_access";
            }
            if(subject.hasRole("admin")){
                return "redirect:/background/index";
            }else{
                return "redirect:/index";
            }
        }catch (AuthenticationException e){
            System.out.println("认证失败");
            return "redirect:/login";
        }
    }

    @GetMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }
}
