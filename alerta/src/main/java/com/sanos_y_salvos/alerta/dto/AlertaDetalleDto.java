package com.sanos_y_salvos.alerta.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlertaDetalleDto {
    private Integer idAlerta;
    private LocalDateTime fechaEnvio;
    private String medioNotificacion;
    private ReporteDTO reporte;
}
