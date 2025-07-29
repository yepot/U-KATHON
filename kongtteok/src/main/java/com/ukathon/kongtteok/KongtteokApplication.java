package com.ukathon.kongtteok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KongtteokApplication {

	public static void main(String[] args) {
		SpringApplication.run(KongtteokApplication.class, args);
	}

}
