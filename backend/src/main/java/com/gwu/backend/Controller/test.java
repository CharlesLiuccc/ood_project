package com.gwu.backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/index")
    public String Hello(){
        return "index";
    }
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
