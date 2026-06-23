package com.sanos_y_salvos.auditoria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "trazabilidad")
@Data
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trazabilidad")
    private Integer idTrazabilidad;

    @NotBlank(message = "El primer nombre de usuario es obligatorio")
    @Size(max = 100, message = "El primer nombre no puede superar los 100 caracteres")
    @Column(name = "p_nombre")
    private String pNombre;

    @Size(max = 100, message = "El segundo nombre no puede superar los 100 caracteres")
    @Column(name = "s_nombre")
    private String sNombre;

    @NotBlank(message = "El apellido paterno de usuario es obligatorio")
    @Size(max = 100, message = "El apellido paterno no puede superar los 100 caracteres")
    @Column(name = "ap_paterno")
    private String apPaterno;

    @Size(max = 100, message = "El apellido materno no puede superar los 100 caracteres")
    @Column(name = "ap_materno")
    private String apMaterno;

    @NotBlank(message = "La acción realizada es obligatoria")
    @Size(max = 150, message = "La acción no puede superar los 150 caracteres")
    private String accion;

    @NotNull(message = "El ID de la direccion es obligatorio")
    @Column(name = "id_comuna")
    private Integer idComuna;

    @NotNull(message = "La fecha y hora del acceso no puede ser nula")
    @Column(name = "fecha_acceso")
    private LocalDateTime fechaAcceso;

    @NotBlank(message = "El resultado del acceso es obligatorio (EXITOSO/FALLIDO)")
    @Size(max = 40, message = "El resultado no puede ser mayor a 40 caracteres")
    @Column(name = "resultado")
    private String resultado;
}