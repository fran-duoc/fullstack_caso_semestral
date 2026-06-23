package com.sanos_y_salvos.mascotas.client;

import com.sanos_y_salvos.mascotas.dto.DuenioDTO;
import com.sanos_y_salvos.mascotas.dto.RazaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TaxonomiaClient {

    @Autowired
    private WebClient webClient;

    public RazaDTO obtenerRaza(Integer id){
        return webClient
                .get()
                .uri("/api/v1/raza/{id}")
                .retrieve()
                .bodyToMono(RazaDTO.class)
                .block();
    }

    public DuenioDTO obtenerDuenio(Integer id){
        return webClient
                .get()
                .uri("/api/v1/duenios/{id}")
                .retrieve()
                .bodyToMono(DuenioDTO.class)
                .block();
    }
}
