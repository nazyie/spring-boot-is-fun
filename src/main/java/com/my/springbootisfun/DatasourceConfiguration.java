package com.my.springbootisfun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
}
