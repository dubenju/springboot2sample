package com.example.demo.service.impl;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    CityServiceImplTest.class,
    RedisServiceTest.class,
    UserServiceImplTest.class})
public class AllServiceImplTests {
}
