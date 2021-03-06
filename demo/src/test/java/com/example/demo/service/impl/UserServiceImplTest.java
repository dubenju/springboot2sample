package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.bean.UserForm;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;
import com.example.demo.repository.LoginUserRepository;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

    private final static Long   TEST_ID        = 1L;
    private final static String TEST_USER_NAME = "test_userName";
    private final static String TEST_EMAIL     = "test_email";
    private final static String TEST_PASSWORD  = "test_password";
    private final static String TEST_AGE       = "10";
    private final static String TEST_ROLES     = "test_roles";

    private UserForm testUserForm;
    private LoginUserId testLoginUserId;
    private LoginUser testLoginUser;


    @Mock // テスト対象で使用するクラスに対してつけるアノテーション
    private LoginUserRepository userRepository;
    /* 順位：@InjectMocksは@Mockの後ろに */
    @InjectMocks // テスト対象のクラスに対してつけるアノテーション
    private UserServiceImpl userServiceImpl;



    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void setup() {
        System.out.println("@Before");
        this.testUserForm = UserForm.builder()
            .id(Long.toString(TEST_ID))
            .userName(TEST_USER_NAME)
            .email(TEST_EMAIL)
            .password(TEST_PASSWORD)
            .age(TEST_AGE)
            .roles(TEST_ROLES)
            .build();
//        this.testUserForm = new UserForm(
//            Long.toString(TEST_ID),
//            TEST_USER_NAME,
//            TEST_EMAIL,
//            TEST_PASSWORD,
//            TEST_AGE,
//            TEST_ROLES);

        this.testLoginUserId = LoginUserId.builder()
            .id(TEST_ID)
            .build();
        System.out.println("1:" + this.testLoginUserId);
        this.testLoginUserId = new LoginUserId(TEST_ID);
        System.out.println("2:" + this.testLoginUserId);

        this.testLoginUser = LoginUser.builder()
            .id(this.testLoginUserId)
            .userName(TEST_USER_NAME)
            .email(TEST_EMAIL)
            .password(TEST_PASSWORD)
            .age(Integer.parseInt(TEST_AGE))
            .roles(TEST_ROLES)
            .build();
    }

    @Test
    public void testDelete() {
        this.userServiceImpl.delete(this.testUserForm);

        Mockito.verify(this.userRepository, Mockito.times(1)).delete(this.testUserForm.getUser());
    }

    @Test
    public void testEdit() {
        Mockito.when(this.userRepository.save(this.testLoginUser)).thenReturn(this.testLoginUser);

        UserForm actual = this.userServiceImpl.edit(this.testUserForm);
        Assertions.assertThat(actual).isEqualTo(this.testUserForm);

        Mockito.verify(this.userRepository, Mockito.times(1)).save(this.testLoginUser);
    }

    // @Ignore
    @Test
    public void testFindUserById_001() {
        System.out.println("@testFindUserById_001"); 
        System.out.println("3:" + this.testLoginUserId);

        Mockito.when(this.userRepository.findById(this.testLoginUserId)).thenReturn(Optional.of(this.testLoginUser));

        UserForm actual = this.userServiceImpl.findUserById(TEST_ID);
        Assertions.assertThat(actual).isEqualTo(this.testUserForm);

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(this.testLoginUserId);
    }

    // @Ignore
    @Test
    public void testFindUserById_002() {
        System.out.println("@testFindUserById_002"); 

        UserForm actual = this.userServiceImpl.findUserById(TEST_ID);
        Assert.assertThat(actual, CoreMatchers.is(IsNull.nullValue()));

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(this.testLoginUserId);
    }

    @Test
    public void testGetUserList() {
        final ArrayList<LoginUser> users = new ArrayList<LoginUser>();
        users.add(this.testLoginUser);
        // thenThrow(new RuntimeException());
        Mockito.when(this.userRepository.findAll()).thenReturn(users);

        List<UserForm> list = this.userServiceImpl.getUserList();// テスト対象のメソッドを実行
        Assertions.assertThat(list.size()).isEqualTo(1); // assertJを使用
    }

    @Test
    public void testSave() {
        Mockito.when(this.userRepository.save(this.testLoginUser)).thenReturn(this.testLoginUser);

        UserForm actual = this.userServiceImpl.save(this.testUserForm);
        Assertions.assertThat(actual).isEqualTo(this.testUserForm);

        Mockito.verify(this.userRepository, Mockito.times(1)).save(this.testLoginUser);
    }

    @After
    public void after() {
        System.out.println("@After");
    }     

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass");
    }
}
