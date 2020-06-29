package com.example.demo.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.pk.LoginUserId;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class LoginUserTest extends BaseVoTest<LoginUser> {

    @Override
    protected LoginUser getT() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(new LoginUserId(1L));
        loginUser.setUserName("car");
        loginUser.setAge(1);
        loginUser.setEmail("email");
        loginUser.setPassword("password");
        loginUser.setRoles("roles");
        return loginUser;
    }

    @Test
    public void test() {
        ArrayList<Class> CLASS_LIST = new ArrayList<Class>();
        CLASS_LIST.add(Car.class);
        try {
            EntityVoTestUtils.justRun(CLASS_LIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testBuider() {
        LoginUser user = LoginUser.builder()
                .age(2)
                .email("email")
                .id(new LoginUserId(2L))
                .password("password")
                .roles("roles")
                .userName("userName")
                .build();
        System.out.println(user);
        System.out.println(user.toBuilder());
    }
}
