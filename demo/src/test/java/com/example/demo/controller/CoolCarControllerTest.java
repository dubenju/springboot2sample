package com.example.demo.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Car;
import com.example.demo.exception.ResourceNotFoundException;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )
public class CoolCarControllerTest extends AbstractTest {
    private String id = "1";
    @Override
    @Before
    public void setUp() {
       super.setUp();
    }

    @Test
    public void test_002_getCarsList() throws Exception {
       String uri = "/cool-cars";
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
          .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
       
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       Car[] carlist = super.mapFromJson(content, Car[].class);
       assertTrue(carlist.length > 0);
    }

    @Test
    public void test_001_createCar() throws Exception {
       String uri = "/cars";
       Car car = new Car();
       car.setId(30L);
       car.setName("Ginger");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();
       
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
//       assertEquals(content, "Product is created successfully");
    }

    @Test
    public void test_002_getCar() throws Exception {
       String uri = "/cars/" + id;
       Car car = new Car();
       car.setName("Lemon");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
//       assertEquals(content, "Product is updated successsfully");
    }

    @Test(expected=ResourceNotFoundException.class)
    public void test_002_getCar_002() throws Exception {
       String uri = "/cars/90" + id;
       Car car = new Car();
       car.setName("Lemon");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(404, status);
       String content = mvcResult.getResponse().getContentAsString();
//       assertEquals(content, "Product is updated successsfully");
    }

    @Test
    public void test_003_updateCar() throws Exception {
       String uri = "/cars";
       Car car = new Car();
       car.setId(Long.valueOf(id));
       car.setName("Lemon");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
//       assertEquals(content, "Product is updated successsfully");
    }

    @Test
    public void test_999_deleteCar() throws Exception {
       String uri = "/cars/" + id;
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
//       assertEquals(content, "Product is deleted successsfully");
    }
}
