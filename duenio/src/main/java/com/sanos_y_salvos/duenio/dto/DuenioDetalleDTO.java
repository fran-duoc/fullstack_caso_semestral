package com.sanos_y_salvos.duenio.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
//esta anotacion es para ordenar el Json en el postman y se lea mejor
@JsonPropertyOrder({
        "idDuenio",
        "run",
        "dvrun",
        "pNombre",
        "sNombre",
        "apPaterno",
        "apMaterno",
        "email",
        "fono",
        "comuna"
})

public class DuenioDetalleDTO {

    private Integer idDuenio;
    private Integer run;
    private String dvrun;
    private String pNombre;
    private String sNombre;
    private String apPaterno;
    private String apMaterno;
    private Integer fono;
    private String email;

    private ComunaDTO comuna;
}