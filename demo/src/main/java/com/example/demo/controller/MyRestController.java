package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity2.second.City;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository2.second.CityRepository;

@RestController
@RequestMapping("/api/v1")
public class MyRestController {
    @Autowired
    private CityRepository repository;

    // http://localhost:8081/api/v1/cities 
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return (List<City>) repository.findAll();
    }

    // http://localhost:8081/api/v1/cities/1
    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId)
        throws ResourceNotFoundException {
        City city = repository.findById(cityId)
          .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));
        return ResponseEntity.ok().body(city);
    }
    
    @PostMapping("/cities")
    public City createCity(@Valid @RequestBody City city) {
        return repository.save(city);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateEmployee(@PathVariable(value = "id") Long cityId,
         @Valid @RequestBody City cityDetails) throws ResourceNotFoundException {
        City city = repository.findById(cityId)
        .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        city.setName(cityDetails.getName());
        city.setPopulation(cityDetails.getPopulation());

        final City updatedCity = repository.save(city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId)
         throws ResourceNotFoundException {
        City employee = repository.findById(cityId)
       .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        repository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
