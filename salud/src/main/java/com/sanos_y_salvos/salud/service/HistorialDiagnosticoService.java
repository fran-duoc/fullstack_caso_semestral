package com.sanos_y_salvos.salud.service;

import com.sanos_y_salvos.salud.client.FuncionarioClient;
import com.sanos_y_salvos.salud.client.MascotaClient;
import com.sanos_y_salvos.salud.dto.FuncionarioDto;
import com.sanos_y_salvos.salud.dto.MascotaDto;
import com.sanos_y_salvos.salud.dto.SaludDetalleDto;
import com.sanos_y_salvos.salud.model.HistorialDiagnostico;
import com.sanos_y_salvos.salud.repository.HistorialDiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialDiagnosticoService {
    @Autowired
    private HistorialDiagnosticoRepository historialDiagnosticoRepository;

    public List<HistorialDiagnostico> getHistorialDiagnosticos(){return historialDiagnosticoRepository.findAll();}

    public HistorialDiagnostico saveHistorial(HistorialDiagnostico historialDiagnostico){return historialDiagnosticoRepository.save(historialDiagnostico);}

    public Optional<HistorialDiagnostico> getHistorialDiagnostico(Integer id){return historialDiagnosticoRepository.findById(id);}

    public HistorialDiagnostico updateHistorialDiagnostico(HistorialDiagnostico diagnostico){return historialDiagnosticoRepository.save(diagnostico);}

    public void deleteHistorialDiagnostico(Integer id){historialDiagnosticoRepository.deleteById(id);}

    @Autowired
    private FuncionarioClient funcionarioClient;
    private MascotaClient mascotaClient;

    public SaludDetalleDto getSaludconFuncionarioMascota(Integer id) {

        HistorialDiagnostico historialDiagnostico = historialDiagnosticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con id: " + id));

        FuncionarioDto funcionario = funcionarioClient.obtenerFuncionario(historialDiagnostico.getRutFuncionario()); // ✅ RUT


        MascotaDto mascota = mascotaClient.obtenerMascota(historialDiagnostico.getIdMascota()); // ✅ id mascota

        SaludDetalleDto dto = new SaludDetalleDto();
        dto.setId(historialDiagnostico.getId());
        dto.setFechaAtencion(historialDiagnostico.getFechaAtencion());
        dto.setObservaciones(historialDiagnostico.getObservaciones());
        dto.setFuncionario(funcionario);
        dto.setMascota(mascota);

        return dto;
    }
}

