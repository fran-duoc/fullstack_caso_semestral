package com.sanos_y_salvos.localizacion.service;

import com.sanos_y_salvos.localizacion.model.Comuna;
import com.sanos_y_salvos.localizacion.repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> getComunas(){
        return comunaRepository.findAll();
    }
    public Comuna saveComuna(Comuna comuna){
        return comunaRepository.save(comuna);
    }
    public Optional<Comuna> getComuna(Integer id){
        return comunaRepository.findById(id);
    }
    public Comuna updateComuna(Comuna comuna){
        return comunaRepository.save(comuna);
    }
    public void deleteComuna(Integer id){
        comunaRepository.deleteById(id);
    }
}
