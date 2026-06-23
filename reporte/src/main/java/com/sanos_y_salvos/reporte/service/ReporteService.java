package com.sanos_y_salvos.reporte.service;

import com.sanos_y_salvos.reporte.client.ComunaClient;
import com.sanos_y_salvos.reporte.dto.ComunaDTO;
import com.sanos_y_salvos.reporte.dto.ReporteDetalleDto;
import com.sanos_y_salvos.reporte.model.Reporte;
import com.sanos_y_salvos.reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getReportes() {
        return reporteRepository.findAll();
    }

    public Reporte saveReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Optional<Reporte> getReporte(Integer id) {
        return reporteRepository.findById(id);
    }

    public Reporte updateReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public void deleteReporte(Integer id) {
        reporteRepository.deleteById(id);
    }

    @Autowired
    private ComunaClient comunaClient;

    public ReporteDetalleDto getReporteDetalle(Integer id){


        Reporte reporte = reporteRepository.findById(id).orElse(null);

        ComunaDTO comuna = comunaClient.obtenerComuna(reporte.getIdComuna());

        ReporteDetalleDto dto = new ReporteDetalleDto();

        dto.setIdReporte(reporte.getIdReporte());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setFechaSuceso(reporte.getFechaSuceso());
        dto.setDescripcion(reporte.getDescripcion());

        dto.setComuna(comuna);

        return dto;

    }
}