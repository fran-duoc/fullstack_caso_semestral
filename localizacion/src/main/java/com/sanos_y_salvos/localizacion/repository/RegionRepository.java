package com.sanos_y_salvos.localizacion.repository;

import com.sanos_y_salvos.localizacion.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Integer> {
}
