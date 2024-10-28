package com.java.config.database;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author hieu.nt60
 * 10/25/2024
 */
@Configuration
public class FlywayConfig {
    @Bean(name = "flywayDbLocal")
    public Flyway flywayDb1(@Qualifier("localDataSource") DataSource localDataSource) {
        return Flyway.configure()
                .dataSource(localDataSource)
                .locations("classpath:db/migration/dblocal")
                .baselineOnMigrate(true)
                .load();
    }
}
