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
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Integer idProvincia;
    @NotBlank(message = "El nombre de la provincia no puede estar vacío.")
    @Column(name = "nombre_provincia")
    private String nombreProvincia;

    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;

}
