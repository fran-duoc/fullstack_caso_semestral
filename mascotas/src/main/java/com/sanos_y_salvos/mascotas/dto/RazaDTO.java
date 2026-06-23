package com.sanos_y_salvos.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@JsonPropertyOrder({
        "idRaza",
        "nombreRaza"
})

@Data
public class RazaDTO {
    private Integer idRaza;
    private String nombreRaza;
}