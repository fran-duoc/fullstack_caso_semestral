package com.sanos_y_salvos.salud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class SaludApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludApplication.class, args);
	}

}
