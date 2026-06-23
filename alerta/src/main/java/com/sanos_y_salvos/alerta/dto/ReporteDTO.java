package com.sanos_y_salvos.alerta.dto;

import lombok.Data;

@Data
public class ReporteDTO {
    private Integer idReporte;
    private String tipoReporte;
    private Integer idComuna;
    private String descripcion;
}