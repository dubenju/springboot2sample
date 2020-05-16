package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class GetItemOpSample {
//    static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
//            new ProfileCredentialsProvider()));
//
//         static String tblName = "ProductList";
//
//         public static void main(String[] args) throws IOException {
//            createItems();
//            retrieveItem();
//
//           //Execute updates
//            updateMultipleAttributes();
//            updateAddNewAttribute();
//            updateExistingAttributeConditionally();
//
//           //Item deletion
//            deleteItem();
//         }
//         private static void createItems() {
//            Table table = dynamoDB.getTable(tblName);
//            try {
//               Item item = new Item()
//                  .withPrimaryKey("ID", 303)
//                  .withString("Nomenclature", "Polymer Blaster 4000")
//                  .withStringSet( "Manufacturers",
//                  new HashSet<String>(Arrays.asList("XYZ Inc.", "LMNOP Inc.")))
//                  .withNumber("Price", 50000)
//                  .withBoolean("InProduction", true)
//                  .withString("Category", "Laser Cutter");
//                  table.putItem(item);
//
//               item = new Item()
//                  .withPrimaryKey("ID", 313)
//                  .withString("Nomenclature", "Agitatatron 2000")
//                  .withStringSet( "Manufacturers",
//                  new HashSet<String>(Arrays.asList("XYZ Inc,", "CDE Inc.")))
//                  .withNumber("Price", 40000)
//                  .withBoolean("InProduction", true)
//                  .withString("Category", "Agitator");
//
//               table.putItem(item);
//            } catch (Exception e) {
//               System.err.println("Cannot create items.");
//               System.err.println(e.getMessage());
//            }
//         }
//         private static void retrieveItem() {
//            Table table = dynamoDB.getTable(tableName);
//            try {
//               Item item = table.getItem("ID", 303, "ID, Nomenclature, Manufacturers", null);
//               System.out.println("Displaying retrieved items...");
//               System.out.println(item.toJSONPretty());
//            } catch (Exception e) {
//               System.err.println("Cannot retrieve items.");
//               System.err.println(e.getMessage());
//            }
//         }
//         private static void updateAddNewAttribute() {
//             Table table = dynamoDB.getTable(tableName);
//             try {
//                Map<String, String> expressionAttributeNames = new HashMap<String, String>();
//                expressionAttributeNames.put("#na", "NewAttribute");
//                UpdateItemSpec updateItemSpec = new UpdateItemSpec()
//                   .withPrimaryKey("ID", 303)
//                   .withUpdateExpression("set #na = :val1")
//                   .withNameMap(new NameMap()
//                   .with("#na", "NewAttribute"))
//                   .withValueMap(new ValueMap()
//                   .withString(":val1", "A value"))
//                   .withReturnValues(ReturnValue.ALL_NEW);
//                   UpdateItemOutcome outcome =  table.updateItem(updateItemSpec);
//
//               //Confirm
//                System.out.println("Displaying updated item...");
//                System.out.println(outcome.getItem().toJSONPretty());
//             } catch (Exception e) {
//                System.err.println("Cannot add an attribute in " + tableName);
//                System.err.println(e.getMessage());
//             }
//          }
//         private static void deleteItem() {
//             Table table = dynamoDB.getTable(tableName);
//             try {
//                DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
//                   .withPrimaryKey("ID", 303)
//                   .withConditionExpression("#ip = :val")
//                   .withNameMap(new NameMap()
//                   .with("#ip", "InProduction"))
//                   .withValueMap(new ValueMap()
//                   .withBoolean(":val", false))
//                   .withReturnValues(ReturnValue.ALL_OLD);
//                DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
//
//               //Confirm
//                System.out.println("Displaying deleted item...");
//                System.out.println(outcome.getItem().toJSONPretty());
//             } catch (Exception e) {
//                System.err.println("Cannot delete item in " + tableName);
//                System.err.println(e.getMessage());
//             }
//          }
}
