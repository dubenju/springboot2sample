package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;

public interface LoginUserRepository extends JpaRepository<LoginUser, LoginUserId> {

    // LoginUser findById(long id);

//    void deleteById(Long id);
}
