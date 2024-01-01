package com.my.springbootisfun;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration
 * -------------
 * - Configuration for profiling also can be done thru @Profile annotation
 */
@Configuration
public class DatasourceConfiguration {

    @Profile("dev")
    @Bean
    public String dataSourceDev() {
        System.out.println("Data source for the DEV environment being loaded");
        System.out.println("================================================");
        return "testing only";
    }

    @Profile("prod")
    @Bean
    public String dataSourceProd() {
        System.out.println("Data source for the PROD environment being loaded");
        System.out.println("=================================================");
        return "testing only";
    }

    /**
     * Configuration (Connection for two database in single spring boot application)
     * =============================================================================
     * - Let said we have two DB instance to be connected
     * - We can do this by setup the @Primary
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary.spring.datasource")
    public DataSource dataSourceV1(DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "secondary.spring.datasource")
    public DataSource dataSourceV2(DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }
}
