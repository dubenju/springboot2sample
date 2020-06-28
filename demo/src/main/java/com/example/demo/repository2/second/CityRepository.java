package com.example.demo.repository2.second;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity2.second.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}

