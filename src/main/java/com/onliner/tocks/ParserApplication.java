package com.onliner.tocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.onliner.tocks.repository")
public class ParserApplication {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		SpringApplication.run(ParserApplication.class, args);
	}
}
