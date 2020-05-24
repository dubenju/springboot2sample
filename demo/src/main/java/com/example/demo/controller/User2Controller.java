//package com.example.demo.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//
//@RestController
//public class User2Controller {
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/init")
//    private void init() throws Exception {
//        userRepository.save(new User("taro"));
//    }
//}
