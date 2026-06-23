package com.sanos_y_salvos.alerta.service;

import com.sanos_y_salvos.alerta.client.DuenioClient;
import com.sanos_y_salvos.alerta.dto.AlertaDetalleDto;
import com.sanos_y_salvos.alerta.dto.DuenioDTO;
import com.sanos_y_salvos.alerta.dto.ReporteDTO;
import com.sanos_y_salvos.alerta.model.Alerta;
import com.sanos_y_salvos.alerta.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository alertaRepository;

    public List<Alerta> getAlertas() {
        return alertaRepository.findAll();
    }

    public Alerta saveAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public Optional<Alerta> getAlerta(Integer id) {
        return alertaRepository.findById(id);
    }

    public Alerta updateAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public void deleteAlerta(Integer id) {
        alertaRepository.deleteById(id);
    }

    @Autowired
    private DuenioClient duenioClient;

    public AlertaDetalleDto getAlertaConDuenio(Integer id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada con id: " + id));


        DuenioDTO duenio = duenioClient.obtenerDuenio(alerta.getIdReporte()); // ajusta según tu modelo

        AlertaDetalleDto dto = new AlertaDetalleDto();
        dto.setIdAlerta(alerta.getIdAlerta());
        dto.setFechaEnvio(alerta.getFechaEnvio());
        dto.setMedioNotificacion(alerta.getMedioNotificacion());
        dto.setDuenio(duenio);

        return dto;
    }
}