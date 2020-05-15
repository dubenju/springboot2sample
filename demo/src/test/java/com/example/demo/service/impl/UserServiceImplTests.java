package com.example.demo.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.bean.UserForm;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;
import com.example.demo.repository.LoginUserRepository;

import junit.framework.Assert;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTests {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private LoginUserRepository userRepository;

    @Test
    public void testGetUserList() {
	List<UserForm> userList = userServiceImpl.getUserList();
	fail("Not yet implemented");
    }
    @Test
    public void testFindUserById() {
	Long id = Mockito.mock(Long.class);
	UserForm user = userServiceImpl.findUserById(id);
	fail("Not yet implemented");
    }
    @Test
    public void testSave() {
//	User user = Mockito.mock(User.class);
//	//stubbing
//	// 测试桩
//	Mockito.when(user.getId()).thenReturn(1L);
        LoginUser user = new LoginUser();
        user.setId(new LoginUserId());
        user.getId().setId(1L);
	user.setUserName("テストユーザ");
	user.setPassword("pwd");
	user.setAge(30);
	UserForm form = new UserForm(user);
	userServiceImpl.save(form);

	LoginUser user2 = userRepository.findById(new LoginUserId(1L)).get();
	Assert.assertEquals(user, user2);
    }

    @Test
    public void testEdit() {
        LoginUser user = Mockito.mock(LoginUser.class);
	   UserForm form = new UserForm(user);
	userServiceImpl.edit(form);
	fail("Not yet implemented");
    }
    @Test
    public void testDelete() {
        LoginUser user = Mockito.mock(LoginUser.class);
    UserForm form = new UserForm(user);
	userServiceImpl.delete(form);
	fail("Not yet implemented");
    }
}
