package com.sanos_y_salvos.auditoria.service;

import com.sanos_y_salvos.auditoria.client.ComunaClient;
import com.sanos_y_salvos.auditoria.dto.AuditoriaDetalleDto;
import com.sanos_y_salvos.auditoria.dto.ComunaDTO;
import com.sanos_y_salvos.auditoria.model.Auditoria;
import com.sanos_y_salvos.auditoria.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaService {

    private AuditoriaRepository auditoriaRepository;
    public List<Auditoria> getAuditorias() {
        return auditoriaRepository.findAll();
    }

    public Auditoria saveAuditoria(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    public Optional<Auditoria> getAuditoria(Integer id) {
        return auditoriaRepository.findById(id);
    }

    public Auditoria updateAuditoria(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    public void deleteAuditoria(Integer id) {
        auditoriaRepository.deleteById(id);
    }

    @Autowired
    private ComunaClient comunaClient;
    public AuditoriaDetalleDto getAuditoriaConComuna(Integer id) {

        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada con id: " + id));

        ComunaDTO comuna = comunaClient.obtenerComuna(auditoria.getIdComuna());

        AuditoriaDetalleDto dto = new AuditoriaDetalleDto();
        dto.setIdTrazabilidad(auditoria.getIdTrazabilidad());
        dto.setPNombre(auditoria.getPNombre());
        dto.setSNombre(auditoria.getSNombre());
        dto.setApPaterno(auditoria.getApPaterno());
        dto.setApMaterno(auditoria.getApMaterno());
        dto.setAccion(auditoria.getAccion());
        dto.setComuna(comuna);

        return dto;
    }

}