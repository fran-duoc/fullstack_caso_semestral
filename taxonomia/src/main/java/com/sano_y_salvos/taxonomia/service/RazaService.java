package com.sano_y_salvos.taxonomia.service;

import com.sano_y_salvos.taxonomia.model.Raza;
import com.sano_y_salvos.taxonomia.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RazaService {
    @Autowired
    private RazaRepository razaRepository;

    public List<Raza> getRazas(){return razaRepository.findAll();}

    public Raza saveRaza(Raza raza){ return razaRepository.save(raza);}

    public Optional<Raza> getRaza(Integer id){return razaRepository.findById(id);}

    public Raza updateRaza(Raza raza){return razaRepository.save(raza);}

    public void deleteRaza(Integer id){razaRepository.deleteById(id);}
}
