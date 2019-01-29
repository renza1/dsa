package com.cmcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DsaEngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(DsaEngineApplication.class, args);
	}
}

