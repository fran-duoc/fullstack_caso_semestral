package com.sanos_y_salvos.salud.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
@Data
@JsonPropertyOrder({

        "id",
        "fechaAtencion",
        "observaciones",
        "funcionario",
        "mascota"
})
public class SaludDetalleDto {
    private Integer id;
    private LocalDate fechaAtencion;
    private String observaciones;

    private FuncionarioDto funcionario;
    private MascotaDto mascota;
}
