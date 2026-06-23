package com.sanos_y_salvos.institucion.service;

import com.sanos_y_salvos.institucion.model.TipoInstitucion;
import com.sanos_y_salvos.institucion.repository.TipoInstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoInstitucionService {
    @Autowired
    private TipoInstitucionRepository tipoInstitucionRepository;

    public List<TipoInstitucion> getTipoInstitucions(){return tipoInstitucionRepository.findAll();}

    public TipoInstitucion saveTipoInstitucion(TipoInstitucion tipoInstitucion){return tipoInstitucionRepository.save(tipoInstitucion);}

    public Optional<TipoInstitucion>getTipoInstitucion(Integer id){return tipoInstitucionRepository.findById(id);}

    public TipoInstitucion updaTipoInstitucion(TipoInstitucion tipoInstitucion){return tipoInstitucionRepository.save(tipoInstitucion);}

    public void deleteTipoInstitucion(Integer id){tipoInstitucionRepository.deleteById(id);}
}
