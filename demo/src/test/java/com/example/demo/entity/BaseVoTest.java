package com.example.demo.entity;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public abstract class BaseVoTest<T> {

    protected abstract T getT();
    /**
     * model的get和set方法
     * 1.子类返回对应的类型
     *2.通过反射创建类的实例
     *3.获取该类所有属性字段，遍历获取每个字段对应的get、set方法，并执行
     */
    private void testGetAndSet() throws IllegalAccessException, InstantiationException, IntrospectionException,
            InvocationTargetException {
        T t = getT();
        Class<?> modelClass = t.getClass();
        Object obj = modelClass.newInstance();
        Field[] fields = modelClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("testGetAndSet:" + f.getName());
            //JavaBean属性名要求：前两个字母要么都大写，要么都小写
            //对于首字母是一个单词的情况，要么过滤掉，要么自己拼方法名
            //f.isSynthetic()过滤合成字段
//            if (f.getName().equals("aLike")
//                    || f.isSynthetic()) {
//                continue;
//            }
            if (f.getName().startsWith("$$") || f.isSynthetic()) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelClass);
            Method get = pd.getReadMethod();
            Method set = pd.getWriteMethod();
            try {
                set.invoke(obj, get.invoke(obj));
            } catch (Exception ex) {
                
            }
        }
    }
 
    @Test
    public void getAndSetTest() throws InvocationTargetException, IntrospectionException,
            InstantiationException, IllegalAccessException {
        this.testGetAndSet();
    }

    @Test
    public void testEqual() throws InstantiationException, IllegalAccessException, IntrospectionException, IllegalArgumentException, InvocationTargetException {
        T t = getT();
        T t2 = getT();
        Assert.assertTrue(t.equals(t));
        Assert.assertTrue(t.equals(t2));
        Assert.assertFalse(t.equals(null));
        Assert.assertFalse(t.equals(Integer.valueOf(1)));

        Class<?> modelClass = t.getClass();
        Object obj = modelClass.newInstance();
        Object obj2 = modelClass.newInstance();
        Field[] fields = modelClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
            if (f.getName().startsWith("$$") || f.isSynthetic()) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelClass);
            Method get = pd.getReadMethod();
            Method set = pd.getWriteMethod();

            System.out.println(obj.hashCode());
            Assert.assertFalse(obj.equals(t));
            Assert.assertFalse(t.equals(obj));
            Assert.assertTrue(t.equals(t));
            Assert.assertTrue(obj.equals(obj));

            try {
                set.invoke(obj, new Object[]{ null });
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
            Assert.assertFalse(obj.equals(t));
            Assert.assertFalse(t.equals(obj));
            Assert.assertTrue(t.equals(t));
            Assert.assertTrue(obj.equals(obj));

            System.out.println(obj + ".equals(" + obj2 + "):" + obj.equals(obj2));
            System.out.println(obj.hashCode());

            set.invoke(obj, EntityVoTestUtils.STATIC_MAP.get(f.getType().getName()));
            Assert.assertFalse(obj.equals(t));
            Assert.assertFalse(t.equals(obj));
            Assert.assertTrue(t.equals(t));
            Assert.assertTrue(obj.equals(obj));

            set.invoke(obj2, EntityVoTestUtils.STATIC_MAP2.get(f.getType().getName()));
            System.out.println(obj);
            System.out.println(obj2);
            System.out.println(obj2.hashCode());
            Assert.assertFalse(obj.equals(obj2));
            Assert.assertFalse(obj2.equals(obj));
            Assert.assertTrue(obj2.equals(obj2));
            Assert.assertTrue(obj.equals(obj));
        }
        System.out.println(obj);
        System.out.println(obj2);
        Assert.assertFalse(obj.equals(obj2));
        Assert.assertFalse(obj2.equals(obj));
        Assert.assertTrue(obj2.equals(obj2));
        Assert.assertTrue(obj.equals(obj));

        for (int i = 0; i < fields.length; i ++) {
            Field f = fields[i];
            System.out.println(f.getName());
            if (f.getName().startsWith("$$") || f.isSynthetic()) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelClass);
            Method get = pd.getReadMethod();
            Method set = pd.getWriteMethod();
            set.invoke(obj, EntityVoTestUtils.STATIC_MAP2.get(f.getType().getName()));
            System.out.println(obj.toString().equals(obj2));
            System.out.println(obj.equals(obj2));
            System.out.println(obj.hashCode());
        }
        for (int i = 0; i < fields.length; i ++) {
            Field f = fields[i];
            System.out.println(f.getName());
            if (f.getName().startsWith("$$") || f.isSynthetic()) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelClass);
            Method get = pd.getReadMethod();
            Method set = pd.getWriteMethod();
            try {
                set.invoke(obj, new Object[]{ null });
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
            System.out.println(obj.toString().equals(obj2));
            System.out.println(obj.equals(obj2));
            System.out.println(obj.hashCode());
        }
        for (int i = 0; i < fields.length; i ++) {
            Field f = fields[i];
            System.out.println(f.getName());
            if (f.getName().startsWith("$$") || f.isSynthetic()) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelClass);
            Method get = pd.getReadMethod();
            Method set = pd.getWriteMethod();
            set.invoke(obj, EntityVoTestUtils.STATIC_MAP0.get(f.getType().getName()));

            System.out.println(obj.toString().equals(obj2));
            System.out.println(obj.equals(obj2));
            System.out.println(obj.hashCode());
        }
    }

    @Test
    public void testHashCode() {
        T t = getT();
        int hashCode = t.hashCode();
        System.out.println(hashCode != 0);
    }
    
    @Test
    public void testToString() {
        T t = getT();
        String str = t.toString();
        System.out.println(str != null);
    }
}
