package com.example.demo.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CarTest extends BaseVoTest<Car> {

    @Override
    protected Car getT() {
        Car car = new Car();
        car.setId(10L);
        car.setName("car");
        return car;
    }

    @Test
    public void test() {
        ArrayList<Class> CLASS_LIST = new ArrayList<Class>();
        CLASS_LIST.add(Car.class);
        try {
            EntityVoTestUtils.justRun(CLASS_LIST);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
