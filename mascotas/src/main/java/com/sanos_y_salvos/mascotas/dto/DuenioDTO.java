package com.sanos_y_salvos.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@JsonPropertyOrder({
        "idDuenio",
        "run",
        "dvrun",
        "pNombre",
        "apPaterno",
        "email",
        "fono",
})

@Data
public class DuenioDTO {
    private Integer idDuenio;
    private String run;
    private String dvrun;
    private String pNombre;
    private String apPaterno;
    private String fono;
    private String email;
}