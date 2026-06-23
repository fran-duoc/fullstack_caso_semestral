package com.sanos_y_salvos.salud.client;

import com.sanos_y_salvos.salud.dto.MascotaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MascotaClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public MascotaDto obtenerMascota(Integer mascotaId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/mascotas/{id}", mascotaId)
                .retrieve()
                .bodyToMono(MascotaDto.class)
                .block();
    }

}
