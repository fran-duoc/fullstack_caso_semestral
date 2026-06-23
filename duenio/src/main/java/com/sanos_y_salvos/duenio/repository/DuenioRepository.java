package com.sanos_y_salvos.duenio.repository;

import com.sanos_y_salvos.duenio.model.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenio, Integer>{
}
