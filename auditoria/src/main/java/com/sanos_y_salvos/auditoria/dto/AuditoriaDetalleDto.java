package com.sanos_y_salvos.auditoria.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "idTrazabilidad",
        "pNombre",
        "sNombre",
        "apPaterno",
        "apMaterno",
        "accion",
        "comuna"
})
public class AuditoriaDetalleDto {
    private Integer idTrazabilidad;
    private String pNombre;
    private String sNombre;
    private String apPaterno;
    private String apMaterno;
    private String accion;
    private ComunaDTO comuna;
}
