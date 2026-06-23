package com.sanos_y_salvos.localizacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_region")
    private Integer idRegion;

    @NotBlank(message = "El nombre de la región no puede estar vacío.")
    @Column(name = "nombre_region")
    private String nombreRegion;

}
