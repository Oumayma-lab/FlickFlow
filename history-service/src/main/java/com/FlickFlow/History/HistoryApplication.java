package com.FlickFlow.History;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.FlickFlow.History.history.repository")
//@EntityScan(basePackages = "com.FlickFlow.History.history.entity")
//@ComponentScan(basePackages = "com.FlickFlow.History.history.service")

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
public class HistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryApplication.class, args);
	}

}
