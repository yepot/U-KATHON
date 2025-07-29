package com.ukathon.newsTo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsToApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsToApplication.class, args);
	}

}
