package com.sano_y_salvos.taxonomia.repository;

import com.sano_y_salvos.taxonomia.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie,Integer> {
}
