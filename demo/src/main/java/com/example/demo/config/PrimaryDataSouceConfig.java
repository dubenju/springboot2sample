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
//        entityManagerFactoryRef="entityManagerFactoryPrimary",
//        transactionManagerRef="transactionManagerPrimary",
//        basePackages= { "com.example.demo.repository" }) // 设置Repository所在位置
//public class PrimaryDataSouceConfig {
//    @Autowired @Qualifier("primaryDataSource")
//    private DataSource primaryDataSource;
//    @Autowired
//    private HibernateProperties hibernateProperties;
//    @Autowired 
//    private JpaProperties jpaProperties;
////    @Primary
////    @Bean(name = "primaryJpaProperties")
////    @ConfigurationProperties(prefix = "spring.jpa")
////    public JpaProperties jpaProperties() {
////        return new JpaProperties();
////    }
//
//    @Primary
//    @Bean(name = "entityManagerPrimary")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryBean(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//       //springBoot2.x这样写就行了
//        Map<String, Object> propertiesMap= hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
//        return builder
//                .dataSource(primaryDataSource)
//                .properties(propertiesMap)
//                .packages("com.example.demo.entity") // 设置实体类所在位置
//                .persistenceUnit("primaryPersistenceUnit")
//                .build();
//    }
//
//   /* // 此方法在springBoot2.x以上版本不适合了
//    
//    private Map<String, String> getVendorProperties(Datasorce datasorce) {
//        return jpaProperties.getHibernateProperties(datasorce);
//    }*/
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
//    }
//
//}
