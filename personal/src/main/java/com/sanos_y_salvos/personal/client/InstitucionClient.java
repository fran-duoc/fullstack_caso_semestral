package com.sanos_y_salvos.personal.client;

import com.sanos_y_salvos.personal.dto.InstitucionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InstitucionClient {
    @Autowired
    private WebClient webClient;

    public InstitucionDTO obtenerInstitucion(Integer id) {
        
        return webClient
                .get()
                .uri("/api/v1/institucion/{id}", id)
                .retrieve()
                .bodyToMono(InstitucionDTO.class)
                .block();
    }
}