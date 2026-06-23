package com.sanos_y_salvos.personal.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
//esta anotacion es para ordenar el Json en el postman y se lea mejor
@JsonPropertyOrder({
        "idFuncionario",
        "run",
        "dvrun",
        "pNombre",
        "sNombre",
        "apPaterno",
        "apMaterno",
        "telefono",
        "email",
        "sede",
        "idCargo",
        "institucion"
})
public class PersonalDetalleDTO {
    private Integer idFuncionario;
    private Integer run;
    private String dvrun;
    private String pNombre;
    private String sNombre;
    private String apPaterno;
    private String apMaterno;
    private Integer telefono;
    private String email;
    private String sede;
    private Integer idCargo;
    private InstitucionDTO institucion;
}