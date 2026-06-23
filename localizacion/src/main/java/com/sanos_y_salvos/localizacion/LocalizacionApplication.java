package com.sanos_y_salvos.localizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class LocalizacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalizacionApplication.class, args);
	}

}
