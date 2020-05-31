package com.example.demo.repository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )
public class TestTblModelRepositoryTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TestTblModelRepositoryTest.class);

    @Autowired
    private TestTblModelRepository testTblModelRepository;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.info("TestTblModelRepositoryTest Begin");
    }
    @BeforeAll
    static void beforeAll() {
        LOGGER.info("TestTblModelRepositoryTest  beforeAll()");
    }

    @Before
    public void setUp() throws Exception {
        Thread.sleep(2000);
    }
    @BeforeEach
    void beforeEach() {
        LOGGER.info("call beforeEach()");
    }
    

    @After
    public void tearDown() throws Exception {
        
    }
    @AfterEach
    void afterEach() {
        LOGGER.info("call afterEach()");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.info("TestTblModelRepositoryTest end.");
    }

    @AfterAll
    static void afterAll() {
        LOGGER.info("call afterAll()");
    }

    @Test
    public void test_000_init_000() {
        LOGGER.info("test_000_init_000");
        LOGGER.info("prepare for testing");
        ClassProperties.getProperties(testTblModelRepository);
        LOGGER.info("TestTblModelRepositoryTest begin");
    }
    @Test
    public void succeedingTest() {
        LOGGER.info("call succeedingTest()");
    }
    @Test
    public void failingTest() {
        LOGGER.info("call failingTest()");
        // fail("a failing test");
    }
    @Test
    @Disabled("for demonstration purposes")
    public void skippedTest() {
        LOGGER.info("call skippedTest()");
        // not executed
    }
}