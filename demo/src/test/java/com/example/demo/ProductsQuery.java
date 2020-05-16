package com.example.demo;
import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;

public class ProductsQuery {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("ProductsLoadData.java");

             DynamoDB dynamoDB = new DynamoDB(client);
             Table table = dynamoDB.getTable("Products");
             HashMap<String, String> nameMap = new HashMap<String, String>();
             nameMap.put("#ID", "ID");
             HashMap<String, Object> valueMap = new HashMap<String, Object>();
             valueMap.put(":xxx", 122);
             QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#ID = :xxx")
                .withNameMap(new NameMap().with("#ID", "ID"))
                .withValueMap(valueMap);

             ItemCollection<QueryOutcome> items = null;
             Iterator<Item> iterator = null;
             Item item = null;
             try {
                System.out.println("Product with the ID 122");
                items = table.query(querySpec);
                iterator = items.iterator();

                while (iterator.hasNext()) {
                   item = iterator.next();
                   System.out.println(item.getNumber("ID") + ": "
                      + item.getString("Nomenclature"));
                }
             } catch (Exception e) {
                System.err.println("Cannot find products with the ID number 122");
                System.err.println(e.getMessage());
             }
        // TODO Auto-generated method stub

    }

}
