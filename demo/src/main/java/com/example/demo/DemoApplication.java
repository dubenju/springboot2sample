package com.example.demo;

import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.common.filter.MDCInsertingServletFilter;
import com.example.demo.config.DemoConfiguration.MyFilter;
import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    ApplicationRunner init(CarRepository repository) {
//        repository.deleteAll();
//        return args -> {
//            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
//                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
//                Car car = new Car();
//                car.setName(name);
//                repository.save(car);
//            });
//            repository.findAll().forEach(System.out::println);
//        };
//    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MDCInsertingServletFilter> registerMDCInsertingServletFilter(
            MDCInsertingServletFilter mdcInsertingServletFilter) {
        FilterRegistrationBean<MDCInsertingServletFilter> registrationBean = new FilterRegistrationBean<MDCInsertingServletFilter>();
        registrationBean.setFilter(mdcInsertingServletFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("MDCInsertingServletFilter");
        registrationBean.setOrder(0);
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean<MyFilter> registerMyFilter(
//            MyFilter myFilter) {
//        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<MyFilter>();
//        registrationBean.setFilter(myFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setName("MyFilter");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
}
