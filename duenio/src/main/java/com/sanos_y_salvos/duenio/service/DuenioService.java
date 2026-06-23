package com.sanos_y_salvos.duenio.service;

import com.sanos_y_salvos.duenio.client.ComunaClient;
import com.sanos_y_salvos.duenio.dto.ComunaDTO;
import com.sanos_y_salvos.duenio.dto.DuenioDetalleDTO;
import com.sanos_y_salvos.duenio.model.Duenio;
import com.sanos_y_salvos.duenio.repository.DuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuenioService {

    @Autowired
    private DuenioRepository duenioRepository;

    public List<Duenio> getDuenios() {
        return duenioRepository.findAll();
    }

    public Duenio saveDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public Optional<Duenio> getDuenio(Integer id) {
        return duenioRepository.findById(id);
    }

    public Duenio updateDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public void deleteDuenio(Integer id) {
        duenioRepository.deleteById(id);
    }





    @Autowired
    private ComunaClient comunaClient;

    public DuenioDetalleDTO getDuenioConComuna(Integer id) {
        Duenio duenio =
                duenioRepository.findById(id).orElse(null);

        ComunaDTO comuna =
                comunaClient.obtenerComuna(duenio.getIdComuna());

        DuenioDetalleDTO dto =
                new DuenioDetalleDTO();

        dto.setIdDuenio(duenio.getIdDuenio());
        dto.setRun(Integer.valueOf(duenio.getRun()));
        dto.setDvrun(duenio.getDvrun());
        dto.setPNombre(duenio.getPNombre());
        dto.setSNombre(duenio.getSNombre());
        dto.setApPaterno(duenio.getApPaterno());
        dto.setApMaterno(duenio.getApMaterno());
        dto.setFono(duenio.getTelefono());
        dto.setEmail(duenio.getEmail());

        dto.setComuna(comuna);

        return dto;
    }
}