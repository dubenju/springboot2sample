package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    private @Value("${spring.redis.host}") String redisHost;
    private @Value("${spring.redis.port}") int redisPort;
    private @Value("${spring.redis.password}") String redisPassword;

    // JSONにシリアライズする場合
    //@Bean
    //public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
    //    return new GenericJackson2JsonRedisSerializer();
    //}

    @Bean
    public JedisConnectionFactory connectionFactory() {
            JedisConnectionFactory factory = new JedisConnectionFactory(); 
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.    setPassword(redisPassword);
        factory.setUsePool(true);
        return factory;
    } 
}
