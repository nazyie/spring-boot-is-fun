package com.my.springbootisfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration
 * --------------
 * - This is how the profiling works
 * - You can parse the argument when running (java -jar APP_NAME.jar --spring.profiles.active=dev | java -jar APP_NAME.jar --spring.profiles.active=prod)
 * - It is recommended to utilize the argument from the SpringBoot itself
 */
@SpringBootApplication
@PropertySource("classpath:${spring.profiles.active:dev}-application.properties")
public class SpringBootIsFunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIsFunApplication.class, args);
	}

}
