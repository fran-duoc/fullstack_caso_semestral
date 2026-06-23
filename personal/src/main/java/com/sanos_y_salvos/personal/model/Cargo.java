package com.sanos_y_salvos.personal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Integer idCargo;

    @NotBlank(message = "El nombre del cargo es obligatorio")
    @Size(max = 50, message = "El nombre del cargo no puede superar los 50 caracteres")
    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @NotBlank(message = "La descripción del cargo es obligatoria")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;
}