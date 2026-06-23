package com.sanos_y_salvos.alerta.client;


import com.sanos_y_salvos.alerta.dto.ReporteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReporteClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ReporteDTO obtenerReporte(Integer id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/api/v1/reportes/" + id)
                .retrieve()
                .bodyToMono(ReporteDTO.class)
                .block();
    }
}
