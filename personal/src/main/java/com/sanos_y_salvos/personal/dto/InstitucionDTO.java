package com.sanos_y_salvos.personal.dto;

import lombok.Data;

@Data
public class InstitucionDTO {
    private Integer idInstitucion;
    private String rutInstitucion;
    private String nombreInstitucion;
    private Integer telefono;
    private Integer idTipo;
}