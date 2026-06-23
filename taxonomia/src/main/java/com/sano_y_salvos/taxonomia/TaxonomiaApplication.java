package com.sano_y_salvos.taxonomia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient    // <- UNICA anotacion necesaria
public class TaxonomiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxonomiaApplication.class, args);
	}

}
