package com.example.demo.controller;
import java.io.IOException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.DemoApplication;
import com.example.demo.common.filter.MDCInsertingServletFilter;
import com.example.demo.config.DemoConfiguration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        DemoApplication apl = new DemoApplication();
        DemoConfiguration  conf = new DemoConfiguration();
       mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
               .addFilters(new MDCInsertingServletFilter(), conf.new MyFilter())
               .build();
//       mockMvc = MockMvcBuilders.standaloneSetup(yourController) // 可添加多个Controller
//               .addMappedInterceptors(new String[]{"/**"}, yourInterceptor)
//               .addFilters(yourFilter).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
       throws JsonParseException, JsonMappingException, IOException {
       
       ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.readValue(json, clazz);
    }
}
