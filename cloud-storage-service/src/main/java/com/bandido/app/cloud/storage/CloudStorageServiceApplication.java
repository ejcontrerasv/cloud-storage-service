package com.bandido.app.cloud.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageServiceApplication.class, args);
	}

}
