package com.sanos_y_salvos.mascotas.dto;

import com.sanos_y_salvos.mascotas.model.Sexo;
import lombok.Data;

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
