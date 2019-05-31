package com.store.fresh.api.background;

import com.store.fresh.entity.Demo;
import com.store.fresh.util.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestApi {

    @PostMapping("/update")
    @ResponseBody
    public List<Demo> test2(@RequestBody List<Demo> demos){//通过该注解可以将http中的字符串json转换为对象
        for (Demo demo:demos
             ) {
            System.out.println(demo.toString());
        }
        return demos;
    }

    @PostMapping("hello")
    public ModelAndView hello(String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",message);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @GetMapping("test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }
}
