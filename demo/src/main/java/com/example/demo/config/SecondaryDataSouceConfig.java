//package com.example.demo.config;
//
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactorySecondary",
//        transactionManagerRef="transactionManagerSecondary",
//        basePackages= { "com.example.demo.repository2" }) // 设置Repository所在位置
//public class SecondaryDataSouceConfig {
//    @Autowired @Qualifier("secondaryDataSource")
//    private DataSource secondaryDataSource;
//    @Autowired
//    private HibernateProperties hibernateProperties;
//    @Autowired 
//    private JpaProperties jpaProperties;
////    @Bean(name = "secondJpaProperties")
////    @ConfigurationProperties(prefix = "spring.jpa.second")
////    public JpaProperties jpaProperties() {
////        return new JpaProperties();
////    }
//
//    @Primary
//    @Bean(name = "entityManagerSecondary")
//    @ConfigurationProperties(prefix="spring.seconddatasource")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryBean(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactorySecondary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//        Map<String, Object> propertiesMap= hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
//        return builder
//                .dataSource(secondaryDataSource)
//                .properties(propertiesMap)
//                .packages("com.example.demo.entity2") // 设置实体类所在位置
//                .persistenceUnit("secondaryPersistenceUnit")
//                .build();
//    }
//
//   /* // 此方法在springBoot2.1以上版本不适合了
//    
//    private Map<String, String> getVendorProperties() {
//        return jpaProperties.getHibernateProperties(new HibernateProperties());
//    }*/
//
//    @Primary
//    @Bean(name = "transactionManagerSecondary")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
//    }
//
//}
