//package com.spring.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.java.job.repository.Dcs",
//        entityManagerFactoryRef = "dcsEntityManager",
//        transactionManagerRef = "dcsTransactionManager")
//public class DcsDBConfiguration {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean dcsEntityManager() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dcsDataSource());
//        em.setPackagesToScan("com.java.job.model.Dcs");
//
//        HibernateJpaVendorAdapter vendorAdapter
//                = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties-card.hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean
//    public DataSource dcsDataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(env.getProperty("spring.card-datasource.url"));
//        hikariConfig.setUsername(env.getProperty("spring.card-datasource.username"));
//        hikariConfig.setPassword(env.getProperty("spring.card-datasource.password"));
//        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        hikariConfig.setAutoCommit(true);
//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setMinimumIdle(2);
//        hikariConfig.setIdleTimeout(30000);
//        hikariConfig.setMaxLifetime(1800000);
//
//        // Thay đổi connectionTestQuery cho Oracle
//        hikariConfig.setConnectionTestQuery("SELECT 1 FROM dual");
//
//        // Cấu hình HikariCP cho retry connection
//        hikariConfig.setInitializationFailTimeout(-1);
//        long connectionTimeoutMillis = 60 * 4 * 60 * 1000;
//        hikariConfig.setConnectionTimeout(connectionTimeoutMillis); // thời gian chờ kết nối
//
//        return new HikariDataSource(hikariConfig);
//    }
//
//
//    @Bean
//    public PlatformTransactionManager dcsTransactionManager() {
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(dcsEntityManager().getObject());
//        return transactionManager;
//    }
//}
