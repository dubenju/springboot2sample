package com.example.demo.repository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.example.demo.entity.User;

public class ClassProperties {

    public static void main(String[] args) {
    User user = new User(1L, "admin", "123", 1);
    ClassProperties.getProperties(user);
    }

    public static void getProperties(Object object){
        // ClassName
        String classFullName = object.getClass().getName();
        System.out.println("ClassFullName：" + classFullName);
        int length = classFullName.length();
        int pos = classFullName.lastIndexOf(".");
        String className = classFullName.substring(pos+1, length);
        System.out.println("className："+className);

        // Methods
        Method[] methods = object.getClass().getDeclaredMethods();
        System.out.println("Methods：");
        for(Method method : methods){
            System.out.println("   " + method.toString());
        }

        // Fields
        Field[] fields = object.getClass().getDeclaredFields();
        System.out.println("Fields：");
        for(Field field : fields){
            System.out.println("   "+field.toString());
        }
    }
}