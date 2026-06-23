package com.sanos_y_salvos.personal.repository;

import com.sanos_y_salvos.personal.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
