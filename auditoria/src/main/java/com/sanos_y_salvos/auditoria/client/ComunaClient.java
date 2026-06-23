
package com.sanos_y_salvos.auditoria.client;

import com.sanos_y_salvos.auditoria.dto.ComunaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ComunaClient {

    private final WebClient webClient;

    public ComunaClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8089").build();
    }

    public ComunaDTO obtenerComuna(Integer id) {
        return this.webClient.get()
                .uri("/api/comunas/{id}", id)
                .retrieve()
                .bodyToMono(ComunaDTO.class)
                .block();
    }
}