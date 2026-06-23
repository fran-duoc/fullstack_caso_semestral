package com.sanos_y_salvos.reporte.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({

"idReporte",
"tipoReporte",
"fechaSuceso",
"descripcion",
"comuna"})

public class ReporteDetalleDto {
    private Integer idReporte;
    private String tipoReporte;
    private LocalDate fechaSuceso;
    private String descripcion;

    private ComunaDTO comuna;
}
