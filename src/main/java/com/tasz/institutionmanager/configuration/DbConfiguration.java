package com.tasz.institutionmanager.configuration;

import com.tasz.institutionmanager.model.RoleEntity;
import com.tasz.institutionmanager.model.UserEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = {UserEntity.class, RoleEntity.class})
public class DbConfiguration {
    @Bean
    @ConfigurationProperties("db.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
