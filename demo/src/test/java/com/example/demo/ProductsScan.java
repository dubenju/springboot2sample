package com.example.demo;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
public class ProductsScan {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("https://dynamodb.ap-northeast-1.amazonaws.com");

             DynamoDB dynamoDB = new DynamoDB(client);
             Table table = dynamoDB.getTable("Products");
             ScanSpec scanSpec = new ScanSpec()
                .withProjectionExpression("#ID, Nomenclature , stat.sales")
                .withFilterExpression("#ID between :start_id and :end_id")
                .withNameMap(new NameMap().with("#ID",  "ID"))
                .withValueMap(new ValueMap().withNumber(":start_id", 120)
                .withNumber(":end_id", 129));

             try {
                ItemCollection<ScanOutcome> items = table.scan(scanSpec);
                Iterator<Item> iter = items.iterator();

                while (iter.hasNext()) {
                   Item item = iter.next();
                   System.out.println(item.toString());
                }
             } catch (Exception e) {
                System.err.println("Cannot perform a table scan:");
                System.err.println(e.getMessage());
             }
    }

}
