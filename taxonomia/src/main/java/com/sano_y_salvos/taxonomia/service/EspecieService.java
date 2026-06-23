package com.sano_y_salvos.taxonomia.service;


import com.sano_y_salvos.taxonomia.model.Especie;
import com.sano_y_salvos.taxonomia.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {
    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> getEspecies(){return especieRepository.findAll();}

    public  Especie saveEspecie( Especie especie){ return especieRepository.save(especie);}

    public Optional<Especie> getEspecie(Integer id){return especieRepository.findById(id);}

    public Especie updateEspecie(Especie especie){return especieRepository.save(especie);}

    public void deleteEspecie(Integer id){especieRepository.deleteById(id);}
}
