package com.sanos_y_salvos.institucion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class InstitucionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstitucionApplication.class, args);
	}

}
