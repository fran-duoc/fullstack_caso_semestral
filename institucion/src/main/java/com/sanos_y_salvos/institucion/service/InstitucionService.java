package com.sanos_y_salvos.institucion.service;

import com.sanos_y_salvos.institucion.model.Institucion;
import com.sanos_y_salvos.institucion.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionService {
    @Autowired
    private InstitucionRepository institucionRepository;

    public List<Institucion> getInstitucions(){return institucionRepository.findAll();}

    public Institucion saveInstitucion(Institucion institucion){return institucionRepository.save(institucion);}

    public Optional<Institucion> getInstitucion(Integer id){return institucionRepository.findById(id);}

    public Institucion updateInstitucion(Institucion institucion){return institucionRepository.save(institucion);}

    public void deleteInsitucion(Integer id){institucionRepository.deleteById(id);}
}
