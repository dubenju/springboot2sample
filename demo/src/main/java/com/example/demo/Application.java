//package com.example.demo;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.example.demo.entity.User;
////import com.example.demo.repository.UserRepository;
//
//@SpringBootApplication
//public class Application {
//
////    @Autowired
////    private UserRepository repository;
//    
//    public static void main(String[] args) {
//        try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)) {
//            Application app = ctx.getBean(Application.class);
//            app.run(args);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void run(String... args) throws Exception {
//        System.out.println("処理開始");
//        
//        User taro = new User("taro", "yamada");
//        repository.save(taro);
//        
//        User jiro = new User("jiro", "yamada");
//        repository.save(jiro);
//        
//        User saburo = new User("saburo", "yamada");
//        repository.save(saburo);
//        
//        User takeshi = new User("takeshi", "suzuki");
//        repository.save(takeshi);
//        
//        List<User> result = repository.findByName("yamada");
//        for(User user : result){
//            System.out.println(user.getId() + " " + user.getName());
//        }
//        
//        jiro.setName("tanaka");
//        repository.save(jiro);
//        
//        repository.delete(saburo);
//        
//        System.out.println("処理終了");
//    }
//}
