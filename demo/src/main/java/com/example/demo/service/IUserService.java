package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.UserForm;

public interface IUserService {
    public List<UserForm> getUserList();

    public UserForm findUserById(long id);

    public UserForm save(UserForm user);

    public UserForm edit(UserForm user);

    public void delete(UserForm id);
}
