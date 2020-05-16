package com.example.demo.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.baeldung.spring.data.dynamodb.repositories")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.region}")
    private String region;

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.credential.profile}")
    private String profile;

//    @Value("${amazon.aws.accesskey}")
//    private String amazonAWSAccessKey;
// 
//    @Value("${amazon.aws.secretkey}")
//    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        return amazonDynamoDB;
//        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration( new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region)).build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new ProfileCredentialsProvider(profile).getCredentials();
//        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
