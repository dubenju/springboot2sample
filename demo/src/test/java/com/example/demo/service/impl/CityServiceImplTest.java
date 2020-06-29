package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity2.second.City;
import com.example.demo.repository2.second.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CityServiceImplTest {

    private final static Long   TEST_ID        = 1L;
    private final static String TEST_NAME      = "test_name";
    private final static int    TEST_POPULATION= 10;

    private City testCity;

    @Mock // テスト対象で使用するクラスに対してつけるアノテーション
    private CityRepository cityRepository;
    /* 順位：@InjectMocksは@Mockの後ろに */
    @InjectMocks // テスト対象のクラスに対してつけるアノテーション
    private CityServiceImpl cityServiceImpl;



    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void setup() {
        System.out.println("@Before");
        this.testCity = City.builder()
            .id(TEST_ID)
            .name(TEST_NAME)
            .population(TEST_POPULATION)
            .build();
    }

    @Test
    public void testFindAll() {
        final ArrayList<City> users = new ArrayList<City>();
        users.add(this.testCity);
        // thenThrow(new RuntimeException());
        Mockito.when(this.cityRepository.findAll()).thenReturn(users);

        List<City> list = this.cityServiceImpl.findAll();// テスト対象のメソッドを実行
        Assertions.assertThat(list.size()).isEqualTo(1); // assertJを使用

        Mockito.verify(this.cityRepository, Mockito.times(1)).findAll();
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
