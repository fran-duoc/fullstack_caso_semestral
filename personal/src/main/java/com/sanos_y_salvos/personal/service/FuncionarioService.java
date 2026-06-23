package com.sanos_y_salvos.personal.service;

import com.sanos_y_salvos.personal.client.InstitucionClient;
import com.sanos_y_salvos.personal.dto.InstitucionDTO;
import com.sanos_y_salvos.personal.dto.PersonalDetalleDTO;
import com.sanos_y_salvos.personal.model.Funcionario;
import com.sanos_y_salvos.personal.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Funcionario saveFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> getFuncionario(Integer id){
        return funcionarioRepository.findById(id);
    }

    public Funcionario updateFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Integer id){
        funcionarioRepository.deleteById(id);
    }

    @Autowired
    private InstitucionClient institucionClient;

    public PersonalDetalleDTO getPersonalConInsti(Integer id){

        Funcionario funcionario =
                funcionarioRepository.findById(id).orElse(null);

        InstitucionDTO institucion =
                institucionClient.obtenerInstitucion(funcionario.getIdInstitucion());

        PersonalDetalleDTO dto =
                new PersonalDetalleDTO();
        dto.setIdFuncionario(funcionario.getIdFuncionario());
        dto.setRun(funcionario.getRun());
        dto.setDvrun(funcionario.getDvrun());
        dto.setPNombre(funcionario.getPNombre());
        dto.setSNombre(funcionario.getSNombre());
        dto.setApPaterno(funcionario.getApPaterno());
        dto.setApMaterno(funcionario.getApMaterno());
        dto.setTelefono(funcionario.getTelefono());
        dto.setEmail(funcionario.getEmail());
        dto.setSede(funcionario.getSede());
        dto.setIdCargo(funcionario.getCargo().getIdCargo());

        dto.setInstitucion(institucion);

        return dto;
    }
}