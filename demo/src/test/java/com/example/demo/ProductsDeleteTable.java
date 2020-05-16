package com.example.demo;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public class ProductsDeleteTable {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("http://localhost:8000");

             DynamoDB dynamoDB = new DynamoDB(client);
             Table table = dynamoDB.getTable("Products");
             try {
                System.out.println("Performing table delete, wait...");
                table.delete();
                table.waitForDelete();
                System.out.print("Table successfully deleted.");
             } catch (Exception e) {
                System.err.println("Cannot perform table delete: ");
                System.err.println(e.getMessage());
             }
    }

}
