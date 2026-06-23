package com.sanos_y_salvos.mascotas.repository;

import com.sanos_y_salvos.mascotas.model.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexoRepository extends JpaRepository<Sexo, Integer> {
}