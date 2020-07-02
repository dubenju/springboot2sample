package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.bean.UserForm;

@RestController
public class AngularController {
    // http://192.168.11.2:8081/testAngular.html
    @GetMapping("/testAngular")
    public UserForm sayHello(){
        System.out.println("==================> Angular");
        UserForm user = new UserForm();
        user.setId("9000");
        user.setUserName("jackson");
        user.setEmail("jackson@email.com");
        user.setPassword("123456");
        user.setAge("40");
        user.setRoles("1");
        return user;
    }

}
