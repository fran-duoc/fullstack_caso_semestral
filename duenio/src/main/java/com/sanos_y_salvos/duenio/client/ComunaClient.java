package com.sanos_y_salvos.duenio.client;

import com.sanos_y_salvos.duenio.dto.ComunaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ComunaClient {

    @Autowired
    private WebClient webClient;

    public ComunaDTO obtenerComuna(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/comuna/{id}", id)
                .retrieve()
                .bodyToMono(ComunaDTO.class)
                .block();
    }
}