package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserForm;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private LoginUserRepository userRepository;

    @Override
    public void delete(UserForm user) {
        this.userRepository.delete(user.getUser());
    }

    @Override
    public UserForm edit(UserForm user) {
        this.userRepository.save(user.getUser());
        return user;
    }

    @Override
    public UserForm findUserById(long id) {
        final Optional<LoginUser> optional = this.userRepository.findById(new LoginUserId(id));
        if (optional.isPresent() == false) {
            return null;
        }
        LoginUser user = optional.get();
        UserForm res = new UserForm(user);
        return res;
    }

    @Override
    public List<UserForm> getUserList() {
        List<UserForm> res = new ArrayList<UserForm>();
        List<LoginUser> list = this.userRepository.findAll();
        for( LoginUser user : list) {
            UserForm form = new UserForm(user);
            res.add(form);
        }
        return res;
    }

    @Override
    public UserForm save(UserForm user) {
        this.userRepository.save(user.getUser());
        return user;
    }

}
