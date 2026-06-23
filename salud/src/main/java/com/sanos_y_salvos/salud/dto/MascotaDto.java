package com.sanos_y_salvos.salud.dto;

import lombok.Data;

@Data
public class MascotaDto {
    private Integer idMascota;
    private String nombre;
    private String raza;
    private String tipoAnimal;
}
