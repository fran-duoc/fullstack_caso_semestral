package com.sanos_y_salvos.mascotas.client;

import com.sanos_y_salvos.mascotas.dto.RazaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TaxonomiaClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public RazaDTO obtenerRazaDto(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8084/api/v1/razas/" + id)
                .retrieve()
                .bodyToMono(RazaDTO.class)
                .block();
    }
}
