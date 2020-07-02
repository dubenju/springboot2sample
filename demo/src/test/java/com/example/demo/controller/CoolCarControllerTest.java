package com.example.demo.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Car;

public class CoolCarControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
       super.setUp();
    }

    @Test
    public void getProductsList() throws Exception {
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
    public void createCar() throws Exception {
       String uri = "/cars";
       Car car = new Car();
//       product.setId(3);
       car.setName("Ginger");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();
       
       int status = mvcResult.getResponse().getStatus();
       assertEquals(201, status);
       String content = mvcResult.getResponse().getContentAsString();
       assertEquals(content, "Product is created successfully");
    }

    @Test
    public void updateCar() throws Exception {
       String uri = "/cars/2";
       Car car = new Car();
       car.setName("Lemon");
       String inputJson = super.mapToJson(car);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       assertEquals(content, "Product is updated successsfully");
    }

    @Test
    public void deleteCar() throws Exception {
       String uri = "/cars/2";
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       assertEquals(content, "Product is deleted successsfully");
    }
}
