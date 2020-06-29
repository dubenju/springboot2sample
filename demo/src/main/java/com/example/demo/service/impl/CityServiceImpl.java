package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity2.second.City;
import com.example.demo.repository2.second.CityRepository;
import com.example.demo.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {
        List<City> cities = (List<City>) this.repository.findAll();
        return cities;
    }
}
