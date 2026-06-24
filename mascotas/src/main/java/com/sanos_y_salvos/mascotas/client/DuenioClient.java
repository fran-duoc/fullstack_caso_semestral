package com.sanos_y_salvos.mascotas.client;


import com.sanos_y_salvos.mascotas.dto.DuenioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DuenioClient {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public DuenioDTO obtenerDuenioDto(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/api/v1/duenios/" + id)
                .retrieve()
                .bodyToMono(DuenioDTO.class)
                .block();
    }
}
