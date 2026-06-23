package com.sanos_y_salvos.alerta.client;

import com.sanos_y_salvos.alerta.dto.DuenioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DuenioClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public DuenioDTO obtenerDuenio(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8079/api/v1/duenios/" + id)
                .retrieve()
                .bodyToMono(DuenioDTO.class)
                .block();
    }
}
