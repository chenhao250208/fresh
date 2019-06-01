package com.store.fresh.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foreground/user")
public class ForeUserController {
    @GetMapping("center")
    public String center(){
        return "foreground/user/personalCenter";
    }
}
