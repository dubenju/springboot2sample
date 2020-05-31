package com.example.demo.controller.web.TEST0001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.web.BaseController;
import com.example.demo.model.service.TEST0001.TEST0001Service;

@Controller
abstract public class TEST0001Controller extends BaseController {
    @Autowired
    protected TEST0001Service Service;
    
    public TEST0001Controller() {
    }

    protected String getFnctionId() {
        return TEST0001_ID;
    }
}
