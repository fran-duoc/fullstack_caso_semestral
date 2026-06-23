package com.sanos_y_salvos.reporte.repository;

import com.sanos_y_salvos.reporte.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}