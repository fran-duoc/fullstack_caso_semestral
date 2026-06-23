package com.sanos_y_salvos.alerta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class AlertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertaApplication.class, args);
	}

}
