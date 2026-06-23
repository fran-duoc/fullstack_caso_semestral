package com.sanos_y_salvos.salud.client;

import com.sanos_y_salvos.salud.dto.FuncionarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FuncionarioClient {
    @Autowired
    private WebClient webClient;

    public FuncionarioDto obtenerFuncionario(String rutFuncionario) {
        return webClient
                .get()
                .uri("http://localhost:8085/api/v1/funcionarios/{id}", rutFuncionario)
                .retrieve()
                .bodyToMono(FuncionarioDto.class)
                .block();
    }
}
