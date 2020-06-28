package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity2.second.City;
import com.example.demo.service.ICityService;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ICityService cityService;

    // http://localhost:8081/showCities
    @GetMapping("/showCities")
    public String findCities(Model model) {

        List<City> cities = (List<City>) cityService.findAll();

        model.addAttribute("cities", cities);

        return "showCities";
    }
}