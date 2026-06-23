package com.sanos_y_salvos.localizacion.service;

import com.sanos_y_salvos.localizacion.model.Region;
import com.sanos_y_salvos.localizacion.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegion(){
        return regionRepository.findAll();
    }
    public Region saveRegion(Region region){
        return regionRepository.save(region);
    }
    public Optional<Region> getRegion(Integer id){
        return regionRepository.findById(id);
    }
    public Region updateRegion(Region region){
        return regionRepository.save(region);
    }
    public void deleteRegion(Integer id){
        regionRepository.deleteById(id);
    }

}
