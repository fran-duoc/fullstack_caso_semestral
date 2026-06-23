package com.sanos_y_salvos.localizacion.repository;

import com.sanos_y_salvos.localizacion.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunaRepository extends JpaRepository<Comuna,Integer> {
}
