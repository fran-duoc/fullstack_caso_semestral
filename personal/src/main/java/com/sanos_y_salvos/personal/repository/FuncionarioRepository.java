package com.sanos_y_salvos.personal.repository;

import com.sanos_y_salvos.personal.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}
