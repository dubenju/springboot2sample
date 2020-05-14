package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserForm;
import com.example.demo.entity.LoginUser;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginUserRepository userRepository;

    @Override
    public List<UserForm> getUserList() {
        List<UserForm> res = new ArrayList<UserForm>();
        List<LoginUser> list = userRepository.findAll();
        for( LoginUser user : list) {
            UserForm form = new UserForm(user);
            res.add(form);
        }
        return res;
    }

    @Override
    public UserForm findUserById(long id) {
        LoginUser user =  userRepository.findById(id);
        UserForm res = new UserForm(user);
        return res;
    }

    @Override
    public void save(UserForm user) {
        userRepository.save(user.getUser());
    }

    @Override
    public void edit(UserForm user) {
        userRepository.save(user.getUser());
    }

    @Override
    public void delete(UserForm id) {
        userRepository.delete(id.getUser());
    }
}
