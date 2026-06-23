package com.sanos_y_salvos.reporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "reporte")
@Data
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Size(max = 100, message = "El tipo de reporte no puede superar los 100 caracteres")
    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @NotNull(message = "La fecha del suceso es obligatoria")
    @Column(name = "fecha_suceso")
    private LocalDate fechaSuceso;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 250, message = "La descripción no puede superar los 250 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El ID del funcionario es obligatorio")
    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    @NotBlank(message = "El estado de la mascota es obligatorio")
    @Size(max = 150, message = "El estado de la mascota no puede superar los 150 caracteres")
    @Column(name = "estado_mascota")
    private String estadoMascota;

    @NotBlank(message = "El estado del reporte es obligatorio")
    @Size(max = 150, message = "El estado del reporte no puede superar los 150 caracteres")
    @Column(name = "estado_reporte")
    private String estadoReporte;

    @NotNull(message = "La comuna es obligatoria")
    @Column(name = "id_comuna")
    private Integer idComuna;
}