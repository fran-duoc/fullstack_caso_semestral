package com.sanos_y_salvos.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sanos_y_salvos.mascotas.model.Sexo;
import lombok.Data;
@JsonPropertyOrder({
        "idMascota",
        "nombreMascota",
        "raza",
        "color",
        "chip",
        "edad",
        "diagnostico",
        "sexo",
        "duenio"
})
@Data
public class MascotaDetalleDTO {
    private Integer idMascota;
    private String nombreMascota;
    private RazaDTO raza;
    private String color;
    private Integer chip;
    private Integer edad;
    private String diagnostico;
    private Sexo sexo;
    private DuenioDTO duenio;
}
