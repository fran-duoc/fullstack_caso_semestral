package com.sanos_y_salvos.mascotas.service;

import com.sanos_y_salvos.mascotas.client.DuenioClient;
import com.sanos_y_salvos.mascotas.client.TaxonomiaClient;
import com.sanos_y_salvos.mascotas.dto.DuenioDTO;
import com.sanos_y_salvos.mascotas.dto.MascotaDetalleDTO;
import com.sanos_y_salvos.mascotas.dto.RazaDTO;
import com.sanos_y_salvos.mascotas.model.Mascota;
import com.sanos_y_salvos.mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> getMascotas() {
        return mascotaRepository.findAll();
    }

    public Mascota saveMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Optional<Mascota> getMascota(Integer id) {
        return mascotaRepository.findById(id);
    }

    public Mascota updateMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void deleteMascota(Integer id) {
        mascotaRepository.deleteById(id);
    }


    @Autowired
    private TaxonomiaClient taxonomiaClient;

    @Autowired
    private DuenioClient duenioClient;

    public MascotaDetalleDTO getMascotaDetalle(Integer id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));

        RazaDTO razaDto = taxonomiaClient.obtenerRazaDto(mascota.getIdRaza());
        DuenioDTO duenioDto = duenioClient.obtenerDuenioDto(mascota.getIdDuenio());

        MascotaDetalleDTO dto = new MascotaDetalleDTO();
        dto.setIdMascota(mascota.getIdMascota());
        dto.setNombreMascota(mascota.getNombreMascota());
        dto.setColor(mascota.getColor());
        dto.setChip(mascota.getChip());
        dto.setEdad(mascota.getEdad());
        dto.setDiagnostico(mascota.getDiagnostico());
        dto.setSexo(mascota.getSexo());

        dto.setRaza(razaDto);
        dto.setDuenio(duenioDto);

        return dto;
    }
}