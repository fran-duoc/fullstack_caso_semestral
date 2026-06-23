package com.sanos_y_salvos.localizacion.service;

import com.sanos_y_salvos.localizacion.model.Provincia;

import com.sanos_y_salvos.localizacion.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<Provincia> getProvincias(){
        return provinciaRepository.findAll();
    }
    public Provincia saveProvincia(Provincia provincia){
        return provinciaRepository.save(provincia);
    }
    public Optional<Provincia> getProvincia(Integer id){
        return provinciaRepository.findById(id);
    }
    public Provincia updateProvincia(Provincia provincia){
        return provinciaRepository.save(provincia);
    }
    public void deleteProvincia(Integer id){
        provinciaRepository.deleteById(id);
    }
}
