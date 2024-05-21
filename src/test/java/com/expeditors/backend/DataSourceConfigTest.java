package com.expeditors.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigTest {

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setUrl("jdbc:postgresql://localhost:5433/adoptapp");
        managerDataSource.setUsername("larku");
        managerDataSource.setPassword("larku");
        managerDataSource.setDriverClassName("org.postgresql.Driver");
        return managerDataSource;
    }

}
