package com.sanos_y_salvos.personal.service;

import com.sanos_y_salvos.personal.model.Cargo;
import com.sanos_y_salvos.personal.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    private CargoRepository cargoRepository;

    public List<Cargo> getCargos(){
        return cargoRepository.findAll();
    }

    public Cargo saveCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    public Optional<Cargo> getCargo(Integer id){
        return cargoRepository.findById(id);
    }

    public Cargo updateCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    public void deleteCargo(Integer id){
        cargoRepository.deleteById(id);
    }
}