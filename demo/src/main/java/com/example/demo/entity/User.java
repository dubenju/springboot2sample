//package com.example.demo.entity;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
//
//@DynamoDBTable(tableName = "Table")
//public class User {
//
//    private String id;
//    private String name;
//
//    public User() {
//    }
//
//    public User(String name) {
//        this.name = name;
//    }
//
//    public User(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    
//    @DynamoDBHashKey
//    @DynamoDBAutoGeneratedKey
//    public String getId() {
//        return id;
//    }
//
//    @DynamoDBAttribute
//    public String getName() {
//        return name;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
