package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Car;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {

}
