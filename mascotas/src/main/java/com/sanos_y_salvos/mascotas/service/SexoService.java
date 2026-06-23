package com.sanos_y_salvos.mascotas.service;

import com.sanos_y_salvos.mascotas.model.Sexo;
import com.sanos_y_salvos.mascotas.repository.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexoService {
    @Autowired
    private final SexoRepository sexoRepository;

    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<Sexo> getSexos() {
        return sexoRepository.findAll();
    }

    public Optional<Sexo> getSexo(Integer id) {
        return sexoRepository.findById(id);
    }

    public Sexo saveSexo(Sexo sexo) {
        return sexoRepository.save(sexo);
    }

    public void deleteSexo(Integer id) {
        sexoRepository.deleteById(id);
    }
}