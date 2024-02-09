package com.example.testdatalogclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestDatalogClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDatalogClientApplication.class, args);
	}

}
