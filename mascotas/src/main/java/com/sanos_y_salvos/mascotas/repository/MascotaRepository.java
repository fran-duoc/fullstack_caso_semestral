package com.sanos_y_salvos.mascotas.repository;

import com.sanos_y_salvos.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}