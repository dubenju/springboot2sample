package com.example.demo.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.TestTblModel;
import com.example.demo.entity.pk.TestTblModelId;

/**
 * のRepositoryテストクラスです。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )
public class TestTblModelRepositoryTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TestTblModelRepositoryTest.class);

    @Autowired
    private TestTblModelRepository testTblModelRepository;

    private TestTblModel model = new TestTblModel();
    private TestTblModelId modelid = new TestTblModelId();
    private TestTblModel modelName = new TestTblModel();
    private Example<TestTblModel> example = Example.of(model);
    private Example<TestTblModel> exampleName = Example.of(modelName);
    private static long cntBef = -1L;
    private static long cntAft = -1L;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.info("TestTblModelRepositoryTest Begin");
    }

    @Before
    public void setUp() throws Exception {
        Thread.sleep(2000);
        // TODO:INSERTするデータを設定してください。
        this.modelid.setTestId("");
        this.model.setId(this.modelid);
        this.model.setTestName("");
        this.model.setCreateDate("");
        this.model.setCreateTime("");
        this.model.setUpdateDate("");
        this.model.setUpdateTime("");
        this.model.setUpdateUserId("");
        this.model.setUpdateWindowId("");
        modelName.setTestName("");
    }

    /**
     * ステップ000 init
     */
    @Test
    public void test_000_init_000() {
        LOGGER.info("test_000_init_000");
        LOGGER.info("prepare for testing");
        ClassProperties.getProperties(testTblModelRepository);
        LOGGER.info("TestTblModelRepositoryTest begin");
    }

    /**
     * ステップ001 count
     */
    @Test
    public void test_001_count_001() {
        LOGGER.info("test_001_count_001 001-001-001【予想】SELECT COUNT(*) FROM TEST_TBL;");
        cntBef = testTblModelRepository.count();
        Assert.assertTrue(cntBef >= 0L);

        LOGGER.info("test_001_count_001 001-001-002【予想】SELECT COUNT(TEST_ID) FROM TEST_TBL WHERE TEST_ID = ? AND TEST_NAME = ? AND CREATE_DATE = ? AND CREATE_TIME = ? AND UPDATE_DATE = ? AND UPDATE_TIME = ? AND UPDATE_USER_ID = ? AND UPDATE_WINDOW_ID = ?;");
        long cntBefModel = testTblModelRepository.count(this.example);
        Assert.assertTrue(cntBefModel >= 0L);
    }

    /**
     * ステップ002 delete
     */
    @Test
    public void test_002_delete_001() {
        LOGGER.info("test_002_delete_001 002-001-001【予想】DELETE FROM TEST_TBL WHERE TEST_NAME = ? AND CREATE_DATE = ? AND CREATE_TIME = ? AND UPDATE_DATE = ? AND UPDATE_TIME = ? AND UPDATE_USER_ID = ? AND UPDATE_WINDOW_ID = ?;");
        /* 存在しない場合、データをinsertして、deleteする。 */
        testTblModelRepository.delete(model);
    }

    /**
     * ステップ003 findById
     */
    @Test(expected=NoSuchElementException.class)
    public void test_003_findById_001() {
        LOGGER.info("test_003_findById_001 003-001-001【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? ;");
        Optional<TestTblModel> opBef = testTblModelRepository.findById(this.modelid);
        /* nullの場合、NoSuchElementExceptionが発生する。 */
        TestTblModel bef = opBef.get();
        Assert.assertNull(bef);

        LOGGER.info("test_003_findById_002 003-001-002【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ?;");
        List<TestTblModel> findAll = testTblModelRepository.findAll(this.example);
        Assert.assertTrue(findAll.size() ==0);
    }

    /**
     * ステップ004 exists
     */
    @Test
    public void test_004_exists_001() {
        LOGGER.info("test_004_exists_001 004-001-001【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? ;");
        boolean bExists1 = testTblModelRepository.exists(this.example);
        Assert.assertFalse(bExists1);

        LOGGER.info("test_004_exists_001 004-001-002【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? AND ;");
        boolean bExists2 = testTblModelRepository.exists(this.exampleName);
        Assert.assertFalse(bExists2);
    }

    /**
     * ステップ005 save
     */
    @Test
    public void test_005_save_001() {
        LOGGER.info("test_005_save_001 005-001-001【予想】INSERT INTO TEST_TBL TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID) VALUES(? , ?, ?, ?, ?, ?, ?, ?);");
        /* save flush saveAndFlush saveAll */
        TestTblModel entity = testTblModelRepository.save(model);
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity, model);
        Assert.assertTrue(entity.equals(model));
        Assert.assertEquals(entity.getId(), model.getId());
        Assert.assertEquals(entity.getTestName(), model.getTestName());
        Assert.assertEquals(entity.getCreateDate(), model.getCreateDate());
        Assert.assertEquals(entity.getCreateTime(), model.getCreateTime());
        Assert.assertEquals(entity.getUpdateDate(), model.getUpdateDate());
        Assert.assertEquals(entity.getUpdateTime(), model.getUpdateTime());
        Assert.assertEquals(entity.getUpdateUserId(), model.getUpdateUserId());
        Assert.assertEquals(entity.getUpdateWindowId(), model.getUpdateWindowId());
    }

    /**
     * ステップ006 findById
     */
    @Test
    public void test_006_findById_002() {
        LOGGER.info("test_006_findById_002 006-002-001【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? ;");
        /* exists existsById findById findAll findOne getOne */
        Optional<TestTblModel> opAft = testTblModelRepository.findById(this.modelid);
        /* nullの場合、NoSuchElementExceptionが発生する。 */
        TestTblModel aft = opAft.get();
        Assert.assertNotNull(aft);
        Assert.assertEquals(aft, model);
        Assert.assertTrue(aft.equals(model));
        Assert.assertEquals(aft.getId(), model.getId());
        Assert.assertEquals(aft.getTestName(), model.getTestName());
        Assert.assertEquals(aft.getCreateDate(), model.getCreateDate());
        Assert.assertEquals(aft.getCreateTime(), model.getCreateTime());
        Assert.assertEquals(aft.getUpdateDate(), model.getUpdateDate());
        Assert.assertEquals(aft.getUpdateTime(), model.getUpdateTime());
        Assert.assertEquals(aft.getUpdateUserId(), model.getUpdateUserId());
        Assert.assertEquals(aft.getUpdateWindowId(), model.getUpdateWindowId());


        LOGGER.info("test_006_findById_002 006-002-002【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ?;");
        List<TestTblModel> findAll = testTblModelRepository.findAll(this.example);
        Assert.assertNotNull(findAll);
        Assert.assertTrue(findAll.size() == 1);
    }

    /**
     * ステップ007 exists
     */
    @Test
    public void test_007_exists_002() {
        LOGGER.info("test_007_exists_002 007-002-001【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? ;");
        /* exists existsById findById findAll findOne getOne */
        boolean bExists1 = testTblModelRepository.exists(this.example);
        Assert.assertTrue(bExists1);


        LOGGER.info("test_007_exists_002 007-002-002【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ? AND #col.Name = ?;");
        boolean bExists2 = testTblModelRepository.exists(this.example);
        Assert.assertTrue(bExists2);
    }

    /**
     * ステップ008 save
     */
    @Test
    public void test_008_save_002() {
        TestTblModel model2 = new TestTblModel();
        model2.setId(this.modelid);
        model2.setTestName(null);
        model2.setCreateDate(null);
        model2.setCreateTime(null);
        model2.setUpdateDate(null);
        model2.setUpdateTime(null);
        model2.setUpdateUserId(null);
        model2.setUpdateWindowId(null);
        LOGGER.info("test_008_save_002 008-002-001【予想】UPDATE TEST_TBL SETTEST_NAME = ?, CREATE_DATE = ?, CREATE_TIME = ?, UPDATE_DATE = ?, UPDATE_TIME = ?, UPDATE_USER_ID = ?, UPDATE_WINDOW_ID = ? WHERE TEST_ID = ?;");
        /* save flush saveAndFlush saveAll */
        TestTblModel entity = testTblModelRepository.save(model2);
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity, model2);
        Assert.assertTrue(entity.equals(model2));
        Assert.assertEquals(entity.getId(), model2.getId());
        Assert.assertEquals(entity.getTestName(), model2.getTestName());
        Assert.assertEquals(entity.getCreateDate(), model2.getCreateDate());
        Assert.assertEquals(entity.getCreateTime(), model2.getCreateTime());
        Assert.assertEquals(entity.getUpdateDate(), model2.getUpdateDate());
        Assert.assertEquals(entity.getUpdateTime(), model2.getUpdateTime());
        Assert.assertEquals(entity.getUpdateUserId(), model2.getUpdateUserId());
        Assert.assertEquals(entity.getUpdateWindowId(), model2.getUpdateWindowId());
    }

    /**
     * ステップ009 delete
     */
    @Test(expected=NoSuchElementException.class)
    public void test_009_delete_002() {
        LOGGER.info("test_009_delete_002 009-002-001【予想】DELETE FROM TEST_TBL WHERE TEST_ID = ?;");
        /* deleteAll */
        testTblModelRepository.delete(model);


        LOGGER.info("test_009_delete_002 009-002-002【予想】SELECT TEST_ID, TEST_NAME, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, UPDATE_USER_ID, UPDATE_WINDOW_ID FROM TEST_TBL WHERE TEST_ID = ? ;");
        /* exists existsById findById findAll findOne getOne */
        Optional<TestTblModel> opAft = testTblModelRepository.findById(this.modelid);
        /* nullの場合、NoSuchElementExceptionが発生する。 */
        TestTblModel aft = opAft.get();
        Assert.assertNull(aft);
    }

    /**
     * ステップ010 count
     */
    @Test
    public void test_010_count_002() {
        LOGGER.info("test_010_count_002 010-002-001【予想】SELECT COUNT(*) FROM TEST_TBL;");
        cntAft = testTblModelRepository.count();
        Assert.assertTrue(cntAft >= 0L);

        LOGGER.info("test_010_count_002 010-002-002【予想】SELECT COUNT(TEST_ID) FROM TEST_TBL WHERE TEST_ID = ? AND TEST_NAME = ? AND CREATE_DATE = ? AND CREATE_TIME = ? AND UPDATE_DATE = ? AND UPDATE_TIME = ? AND UPDATE_USER_ID = ? AND UPDATE_WINDOW_ID = ?;");
        long cntBefModel = testTblModelRepository.count(this.example);
        Assert.assertTrue(cntBefModel >= 0L);
    }

    @Test
    @Disabled("for demonstration purposes")
    public void skippedTest() {
        LOGGER.info("call skippedTest()");
        // not executed
    }

    @After
    public void tearDown() throws Exception {
        this.modelid.setTestId(null);
        this.model.setId(null);
        this.model.setTestName(null);
        this.model.setCreateDate(null);
        this.model.setCreateTime(null);
        this.model.setUpdateDate(null);
        this.model.setUpdateTime(null);
        this.model.setUpdateUserId(null);
        this.model.setUpdateWindowId(null);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.info("TestTblModelRepositoryTest end.");
    }

}
