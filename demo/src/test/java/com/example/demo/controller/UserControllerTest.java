package com.example.demo.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        // 独立测试方式
        // mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        // 集成 Web环境方式
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    
    
    
    @Test
    public void test_001_Index() {
        System.out.println("test_001_Index begin");
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.ALL))
                // 302 Found
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
                // 画面遷移確認
                // assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/list");
                System.out.println(mvcResult.getResponse().getContentAsString()); 
        } catch(Exception ex) {
            System.out.println("★test_001_Index abend " + ex);
        }
        System.out.println("test_001_Index end");
    }

    
    
    @Test
    public void test_002_List() {
        System.out.println("test_002_List begin");
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/list")
                .accept(MediaType.ALL))
                // 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "user/list");
        } catch(Exception ex) {
            System.out.println("★test_002_List abend " + ex);
        }
        System.out.println("test_002_List end");
    }

    
    @Test
    public void test_003_ToAdd() {
        System.out.println("test_003_ToAdd begin");
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/toAdd")
                .accept(MediaType.ALL))
                // 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "user/userAdd");
        } catch(Exception ex) {
            System.out.println("★test_003_ToAdd abend " + ex);
        }
        System.out.println("test_003_ToAdd end");
    }

    
    @Test
    public void test_004_Add_001() {
        System.out.println("test_004_Add_001 begin");
        try {
            User user = new User();
            user.setId(2L);
            Example<User> example = Example.of(user);
            List<User> users = userRepository.findAll(example);
            if (users != null && users.size() > 0) {
                userRepository.deleteInBatch(users);
            }

            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/add")
                    .param("id", "")
                    .param("userName", "2")
                    .param("password", "2")
                    .param("Age", "2")
                .accept(MediaType.ALL))
                // 302 Found
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/list");
            
            // 画面出力チェック
            user.setId(2L);
            users = userRepository.findAll(example);
            assertTrue(users.size() == 1);
            User user2 = users.get(0);
            assertEquals(user2.getUserName(), "2");
            assertEquals(user2.getPassword(), "2");
            assertEquals(user2.getAge(), Integer.valueOf(2));
            
            // 画面表示のチェックは？

        } catch(Exception ex) {
            System.out.println("★test_004_Add abend " + ex);
            fail("ERROR");
        }
        System.out.println("test_004_Add end");
    }

    
    @Test
    public void test_004_Add_999() {
        System.out.println("test_004_Add begin");
        try {
            User user = new User();
            user.setId(2L);
            Example<User> example = Example.of(user);
            List<User> users = userRepository.findAll(example);
            if (users != null && users.size() > 0) {
                userRepository.deleteInBatch(users);
            }

            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/add")
                    .param("id", "2")
                    .param("userName", "2")
                    .param("password", "2")
                    .param("Age", "2")
                .accept(MediaType.ALL))
                // 302 Found
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/list");
            
            // 画面出力チェック
            user.setId(2L);
            users = userRepository.findAll(example);
            assertTrue(users.size() == 1);
            User user2 = users.get(0);
            assertEquals(user2.getUserName(), "2");
            assertEquals(user2.getPassword(), "2");
            assertEquals(user2.getAge(), Integer.valueOf(2));
            
            // 画面表示のチェックは？

        } catch(Exception ex) {
            System.out.println("★test_004_Add abend " + ex);
            fail("ERROR");
        }
        System.out.println("test_004_Add end");
    }

    @Test
    public void test_005_ToEdit() {
        System.out.println("test_005_ToEdit begin");
        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/toEdit")
                    .param("id", "2")
                .accept(MediaType.ALL))
                // 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "user/userEdit");
        } catch(Exception ex) {
            System.out.println("★test_005_ToEdit abend " + ex);
            fail("ERROR");
        }
        System.out.println("test_005_ToEdit end");
    }

    @Test
    public void test_006_Edit() {
        System.out.println("test_006_Edit begin");
        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/edit")
                    .param("id", "2")
                    .param("userName", "3")
                    .param("password", "3")
                    .param("Age", "23")
                .accept(MediaType.ALL))
                    // 302 Found
                    .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/list");
        } catch(Exception ex) {
            System.out.println("★test_006_Edit abend " + ex);
            fail("ERROR");
        }
        System.out.println("test_006_Edit end");
    }

    @Test
    public void test_007_Delete() {
        System.out.println("test_007_Delete begin");
        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/delete")
                    .param("id", "2")
                .accept(MediaType.ALL))
                // 302 Found
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
            // 画面遷移確認
            assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/list");
        } catch(Exception ex) {
            System.out.println("★test_007_Delete abend " + ex);
            fail("ERROR");
        }
        System.out.println("test_007_Delete end");
    }

}
