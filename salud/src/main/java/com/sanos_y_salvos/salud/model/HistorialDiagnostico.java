package com.sanos_y_salvos.salud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "historial_diagnostico")
public class HistorialDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha de atención es obligatoria")
    @Column(name = "fecha_atencion")
    private LocalDate fechaAtencion;

    @NotBlank(message = "Las observaciones clínicas no pueden estar vacías")
    @Size(max = 300, message = "Las observaciones no pueden superar los 300 caracteres")
    private String observaciones;


    @NotNull(message = "El ID de la mascota es obligatorio")
    @Column(name = "id_mascota")
    private Integer idMascota;

    @NotBlank(message = "El RUT del funcionario es obligatorio")
    @Size(max = 12, message = "El RUT del funcionario no puede superar los 12 caracteres")
    @Column(name = "rut_funcionario")
    private String rutFuncionario;
}
