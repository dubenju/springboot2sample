package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/application.properties")
public class PropertyUtils {

    @Autowired
    private Environment env;
    
    public static final String SEARCH_LIMIT = "search.limit";
    public static final String PERMISSION = "permission";
    
    public String get(String scope, String key) {
        return env.getProperty(scope + "." + key);
    }
}
