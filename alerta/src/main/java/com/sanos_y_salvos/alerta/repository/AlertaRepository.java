package com.sanos_y_salvos.alerta.repository;

import com.sanos_y_salvos.alerta.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
}