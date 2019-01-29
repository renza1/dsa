package com.cmcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DsaManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DsaManagerApplication.class, args);
	}
}

