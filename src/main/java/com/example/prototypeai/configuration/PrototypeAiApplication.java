package com.example.prototypeai.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.prototypeai"})

public class PrototypeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeAiApplication.class, args);
	}

}
