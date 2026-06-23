package com.sanos_y_salvos.duenio.client;


import com.sanos_y_salvos.duenio.dto.ComunaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ComunaClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ComunaDTO obtenerComuna(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8088/api/v1/comunas/" + id)
                .retrieve()
                .bodyToMono(ComunaDTO.class)
                .block();
    }
}