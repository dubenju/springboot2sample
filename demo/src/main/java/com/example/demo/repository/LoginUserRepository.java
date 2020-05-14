package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

    LoginUser findById(long id);

    void deleteById(Long id);
}
