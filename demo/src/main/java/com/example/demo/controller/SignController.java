package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignController {
    @RequestMapping("/sign")
    public String sign() {
        return "user/sign";
    }
    @RequestMapping("/angular")
    public String angular() {
        return "AngularJS/20200612";
    }
}
