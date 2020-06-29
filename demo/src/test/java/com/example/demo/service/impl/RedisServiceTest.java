package com.example.demo.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RedisServiceTest {
    private final static String TEST_KEY       = "test_key";
    private final static String TEST_VALUE     = "test_value";

    private @Mock RedisConnection redisConnectionMock;
    private @Mock RedisConnectionFactory redisConnectionFactoryMock;
    private @Mock ValueOperations<String, Object> valueOperations;
    private @Mock RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    private @InjectMocks RedisService redisServiceImpl;

    @Before
    public void setUp() {
        Mockito.when(redisConnectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);

        redisTemplate.setConnectionFactory(redisConnectionFactoryMock);
        redisTemplate.afterPropertiesSet();
    }

//    @Ignore
    @Test
    public void testDel_001() {
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(this.redisTemplate.delete(TEST_KEY)).thenReturn(true);
        long res = this.redisServiceImpl.del(TEST_KEY);
        Assert.assertEquals(1L, res);
        Mockito.verify(this.redisTemplate, Mockito.times(1)).delete(TEST_KEY);
    }

    @Test
    public void testDel_002() {
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(this.redisTemplate.delete("test_key2")).thenReturn(false);
        long res = this.redisServiceImpl.del("test_key2");
        Assert.assertEquals(0L, res);
        Mockito.verify(this.redisTemplate, Mockito.times(1)).delete("test_key2");
    }

//    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void testDel_003() {
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(this.valueOperations.get(TEST_KEY)).thenReturn(TEST_VALUE);
        long res = this.redisServiceImpl.del(TEST_KEY, "test_key2");
        Assert.assertEquals(0L, res);
        Mockito.verify(this.redisTemplate, Mockito.times(1)).delete(CollectionUtils.arrayToList(new String [] {TEST_KEY, "test_key2"}));
    }

//    @Ignore
    @Test
    public void testDel_004() {
        long res = this.redisServiceImpl.del();
        Assert.assertEquals(0L, res);
    }

    @Test
    public void testDel_005() {
        long res = this.redisServiceImpl.del("");
        Assert.assertEquals(0L, res);
    }

//    @Ignore
    @Test
    public void testGet_001() {
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(this.valueOperations.get(TEST_KEY)).thenReturn(TEST_VALUE);
        Object value = this.redisServiceImpl.get(TEST_KEY);
        Assert.assertEquals(TEST_VALUE, value);
        Mockito.verify(this.redisTemplate, Mockito.times(1)).opsForValue();
    }

//    @Ignore
    @Test
    public void testGet_002() {
        String nullValue = null;
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(this.valueOperations.get(null)).thenReturn(nullValue);
        Object value = this.redisServiceImpl.get(null);
        Assert.assertNull(value);
        Mockito.verify(this.redisTemplate.opsForValue(), Mockito.never()).get(null);
    }

//    @Ignore
    @Test
    public void testSet_001() {
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(valueOperations);

        boolean res = this.redisServiceImpl.set(TEST_KEY, TEST_VALUE);
        Assert.assertTrue(res);
        Mockito.verify(this.redisTemplate.opsForValue(), Mockito.times(1)).set(TEST_KEY, TEST_VALUE);
    }

    @Test
    public void testSet_002() {
        ValueOperations<String, Object> nullOperations = null;
        Mockito.when(this.redisTemplate.opsForValue()).thenReturn(nullOperations);

        boolean res = this.redisServiceImpl.set(TEST_KEY, TEST_VALUE);
        Assert.assertFalse(res);
        Mockito.verify(this.redisTemplate, Mockito.times(1)).opsForValue();
    }
}
