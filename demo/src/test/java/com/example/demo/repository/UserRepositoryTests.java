package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )
public class UserRepositoryTests {
    private static Logger logger = LoggerFactory.getLogger(UserRepositoryTests.class);
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;
    
    private User user = new User(1L, "admin", "123", 1);;
    private User userName = new User();;
    private Example<User> example = Example.of(user);
    private Example<User> exampleName = Example.of(userName);
    private static long cntBef = -1L;
    private static long cntAft = -1L;

    @BeforeClass
    public static void beforeClass() {
        logger.info("UserRepositoryTests begin");
    }

    @Before
    public void beforeTest() {
        userName.setUserName("admin");
    }
    @After
    public void afterTest() {
    }

    /**
     * ステップ000 init
     */
    @Test
    public void test_000_init_000() {
        logger.info("test_000_init_000");
        logger.info("prepare for testing");
        ClassProperties.getProperties(userRepository);
        logger.info("UserRepositoryTest begin");
        userName.setUserName("admin");
    }

    /**
     * ステップ001 count
     */
    @Test
    public void test_001_count_001() {
        logger.info("test_001_count_001");
        logger.info("【予想】SELECT COUNT(*) FROM USER;");
        cntBef = userRepository.count();
        Assert.assertTrue(cntBef >= 0L);
        logger.info("【予想】SELECT COUNT(*) FROM USER WHERE ID = ? AND ;");
        long cntBefUser = userRepository.count(this.example);
        Assert.assertTrue(cntBefUser >= 0L);
    }

    /**
     * ステップ002 delete
     */
    @Test(expected=EmptyResultDataAccessException.class)
    public void test_002_delete_001() {
        logger.info("test_002_delete_001");
        logger.info("【予想】DELETE FROM USER WHERE ID = ? AND ;");
        userRepository.delete(user);
        logger.info("【予想】DELETE FROM USER WHERE ID = ?;");
        // userRepository.deleteById(user);
        // org.springframework.data.jpa.repository.support.SimpleJpaRepository
        // when delete data is not exist, Exception
        userRepository.deleteById(user.getId());
    }


    /**
     * ステップ003 findById
     */
    @Test
    public void test_003_findById_001() {
        logger.info("test_003_findById_001");
        logger.info("【予想】SELECT * FROM USER WHERE ID=?;");
        User bef = userRepository.findById(user.getId());
        Assert.assertNull(bef);
        logger.info("【予想】SELECT * FROM USER WHERE ID=?;");
        List<User> findAll = userRepository.findAll(this.example);
        Assert.assertTrue(findAll.size() == 0);
        
    }

    /**
     * ステップ004 exists
     */
    @Test
    public void test_004_exists_001() {
        logger.info("test_004_exists_001");
        logger.info("【予想】SELECT * FROM USER WHERE ALL COLUMN;");
        // QBE :Query by Example
        boolean bExists1 = userRepository.exists(example);
        Assert.assertFalse(bExists1);
        logger.info("【予想】SELECT * FROM USER WHERE USER_NAME = ?;");
        boolean bExists2 = userRepository.exists(exampleName);
        Assert.assertFalse(bExists2);
        logger.info("【予想】SELECT COUNT(*) FROM USER WHERE ID=?;");
        boolean bExists = userRepository.existsById(user.getId());
        Assert.assertFalse(bExists);
    }

    /**
     * ステップ005  save
     */
    @Test
    public void test_005_save_001() {
        logger.info("test_005_save_001");
        logger.info(user.toString());
        // upsert
        logger.info("【予想】INSERT INTO USER VALUES(?, ?, ...);");
        /* save flush saveAndFlush saveAll */
        User entity = userRepository.save(user);
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity, user);
        Assert.assertTrue(entity.equals(user));
        Assert.assertEquals(entity.getId(), user.getId());
        Assert.assertEquals(entity.getUserName(), user.getUserName());
        Assert.assertEquals(entity.getPassword(), user.getPassword());
        Assert.assertEquals(entity.getAge(), user.getAge());
        // Explicitly flush so any CUD query that is left behind is send to the database before rolling back
        // entityManager.flush();
    }

    /**
     * ステップ006 findById
     */
    @Test
    public void test_006_findById_002() {
        logger.info("test_006_findById_002");
        logger.info("【予想】SELECT * FROM USER WHERE ID = ?;");
        /* exists existsById findById findAll findOne getOne */
        User aft = userRepository.findById(user.getId());
        Assert.assertNotNull(aft);
        Assert.assertEquals(aft, user);
        Assert.assertTrue(aft.equals(user));
        Assert.assertEquals(aft.getId(), user.getId());
        Assert.assertEquals(aft.getUserName(), user.getUserName());
        Assert.assertEquals(aft.getPassword(), user.getPassword());
        Assert.assertEquals(aft.getAge(), user.getAge());

        logger.info("【予想】SELECT * FROM USER WHERE ID=?;");
        List<User> findAll = userRepository.findAll(this.example);
        Assert.assertNotNull(findAll);
        Assert.assertTrue(findAll.size() == 1);
    }

    /**
     * ステップ007 exists
     */
    @Test
    public void test_007_exists_002() {
        logger.info("test_007_exists_002");
        logger.info("【予想】SELECT * FROM USER WHERE ALL COLUMN");
        // QBE :Query by Example
        boolean bExists1 = userRepository.exists(example);
        Assert.assertTrue(bExists1);
        logger.info("【予想】SELECT * FROM USER WHERE NOT NULL COLUMNS;");
        boolean bExists2 = userRepository.exists(exampleName);
        Assert.assertFalse(bExists2);
        logger.info("【予想】SELECT COUNT(*) FROM USER WHERE ID=?;");
        boolean bExists = userRepository.existsById(user.getId());
        Assert.assertTrue(bExists);
    }

    /**
     * ステップ008  save
     */
    @Test
    public void test_008_save_002() {
        logger.info("test_008_save_002");
        User user2 = new User(1L, "administrator", "admin", 23);
        logger.info("test save");
        logger.info(user2.toString());
        // upsert
        logger.info("【予想】UPDATE USER SET USER_NAME = ?, ... WHERE ID = ?;");
        /* save flush saveAndFlush saveAll */
        User entity = userRepository.save(user2);
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity, user2);
        Assert.assertTrue(entity.equals(user2));
        Assert.assertEquals(entity.getId(), user2.getId());
        Assert.assertEquals(entity.getUserName(), user2.getUserName());
        Assert.assertEquals(entity.getPassword(), user2.getPassword());
        Assert.assertEquals(entity.getAge(), user2.getAge());
        // Explicitly flush so any CUD query that is left behind is send to the database before rolling back
        // entityManager.flush();
    }

    /**
     * ステップ009 delete
     */
    @Test(expected=EmptyResultDataAccessException.class) 
    public void test_009_delete_002() {
        logger.info("test_009_delete_002");
        logger.info("【予想】DELETE FROM USER WHERE ;");
        /* delete deleteById deleteInBatch deleteAllInBatch deleteAll */
        userRepository.delete(user);
        logger.info("【予想】SELECT * FROM USER WHERE ID = ?;");
        User aftDel = userRepository.findById(user.getId());
        Assert.assertNull(aftDel);
        logger.info("【予想】DELETE FROM USER WHERE ;");
        // userRepository.deleteById(user);
        // org.springframework.data.jpa.repository.support.SimpleJpaRepository
        // when delete data is not exist, Exception
        userRepository.deleteById(user.getId());
    }

    /**
     * ステップ010 count
     */
    @Test
    public void test_010_count_002() {
        logger.info("test_010_count_002");
        logger.info("【予想】SELECT COUNT(*) FROM USER;");
        cntAft = userRepository.count();
        Assert.assertTrue(cntAft >= 0L);
        logger.info("【予想】SELECT COUNT(*) FROM USER WHERE NOT NULL;");
        long cntBefUser = userRepository.count(this.example);
        Assert.assertTrue(cntBefUser >= 0L);
        Assert.assertEquals(cntBef, cntAft);
    }

    @AfterClass
    public static void afterClass() {
        logger.info("UserRepositoryTest end");
    }
}
