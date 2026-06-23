package com.sanos_y_salvos.salud.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class FuncionarioDto {
    private Integer idFuncionario;
    private String runFuncionario;
    private String dvRun;
    private String pNombre;
    private String apPaterno;
}
