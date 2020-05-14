package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.UserForm;
import com.example.demo.entity.LoginUser;

public interface UserService {
    public List<UserForm> getUserList();

    public UserForm findUserById(long id);

    public void save(UserForm user);

    public void edit(UserForm user);

    public void delete(UserForm id);
}
