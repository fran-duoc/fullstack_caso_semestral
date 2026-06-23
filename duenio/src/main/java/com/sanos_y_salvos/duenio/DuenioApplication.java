package com.sanos_y_salvos.duenio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class DuenioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuenioApplication.class, args);
	}

}
