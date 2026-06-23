package com.sanos_y_salvos.alerta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
@Data
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    @NotNull(message = "El ID del reporte es obligatorio")
    @Column(name = "id_reporte")
    private Integer idReporte;

    @NotNull(message = "El ID de la comuna es obligatorio")
    @Column(name = "id_comuna")
    private Integer idComuna;

    @NotNull(message = "La fecha de envío no puede ser nula")
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @NotBlank(message = "El medio de notificación es obligatorio")
    @Size(max = 50, message = "El medio no puede superar los 50 caracteres")
    @Column(name = "medio_notificacion")
    private String medioNotificacion;
}