package com.sanos_y_salvos.reporte.client;

import com.sanos_y_salvos.reporte.dto.ComunaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ComunaClient {
    @Autowired
    private WebClient webClient;

    public ComunaDTO obtenerComuna(Integer idComuna){
        return webClient.get()
                .uri("http://localhost:8085/api/v1/comuna/{id}", idComuna)
                .retrieve()
                .bodyToMono(ComunaDTO.class)
                .block();
    }
}
