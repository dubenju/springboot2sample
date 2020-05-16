package com.example.demo;
import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
public class ProductsCreateTable {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("https://dynamodb.ap-northeast-1.amazonaws.com");

             DynamoDB dynamoDB = new DynamoDB(client);
             String tableName = "Products";
             try {
                System.out.println("Creating the table, wait...");
                Table table = dynamoDB.createTable (tableName,
                   Arrays.asList (
                      new KeySchemaElement("ID", KeyType.HASH),//the partition key
                                                               //the sort key
                      new KeySchemaElement("Nomenclature", KeyType.RANGE)
                   ),
                   Arrays.asList (
                      new AttributeDefinition("ID", ScalarAttributeType.N),
                      new AttributeDefinition("Nomenclature", ScalarAttributeType.S)
                   ),
                   new ProvisionedThroughput(10L, 10L)
                );
                table.waitForActive();
                System.out.println("Table created successfully.  Status: " +
                   table.getDescription().getTableStatus());

             } catch (Exception e) {
                System.err.println("Cannot create the table: ");
                System.err.println(e.getMessage());
             }
        
    }

}
