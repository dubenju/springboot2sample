package com.example.demo.repository;
import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
        List<User> findByName(String name);
}
