package com.example.demo;
import java.io.File;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProductsLoadData {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("https://dynamodb.ap-northeast-1.amazonaws.com");

             DynamoDB dynamoDB = new DynamoDB(client);
             Table table = dynamoDB.getTable("Products");
             JsonParser parser = new JsonFactory().createParser(new File("productinfo.json"));

             JsonNode rootNode = new ObjectMapper().readTree(parser);
             Iterator<JsonNode> iter = rootNode.iterator();
             ObjectNode currentNode;

             while (iter.hasNext()) {
                currentNode = (ObjectNode) iter.next();
                int ID = currentNode.path("ID").asInt();
                String Nomenclature = currentNode.path("Nomenclature").asText();

                try {
                   table.putItem(new Item()
                      .withPrimaryKey("ID", ID, "Nomenclature", Nomenclature)
                      .withJSON("Stat", currentNode.path("Stat").toString()));
                   System.out.println("Successful load: " + ID + " " + Nomenclature);
                } catch (Exception e) {
                   System.err.println("Cannot add product: " + ID + " " + Nomenclature);
                   System.err.println(e.getMessage());
                   break;
                }
             }
             parser.close();
    }

}
