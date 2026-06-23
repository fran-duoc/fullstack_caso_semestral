package com.sanos_y_salvos.personal.client;

import com.sanos_y_salvos.personal.dto.InstitucionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InstitucionClient {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public InstitucionDTO obtenerInstiDto(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/institucion/" + id)
                .retrieve()
                .bodyToMono(InstitucionDTO.class)
                .block();
    }
}