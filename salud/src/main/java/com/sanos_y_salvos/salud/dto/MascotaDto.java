package com.sanos_y_salvos.salud.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({

        "idMascota",
        "nombre",
        "raza",
        "tipoAnimal"
})
public class MascotaDto {
    private Integer idMascota;
    private String nombre;
    private String raza;
    private String tipoAnimal;
}
