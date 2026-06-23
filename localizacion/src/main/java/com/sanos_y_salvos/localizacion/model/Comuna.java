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
@Table(name = "comuna")
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna")
    private Integer idComuna;
    @NotBlank(message = "El nombre de la comuna no puede estar vacío.")
    @Column(name = "nombre_comuna")
    private String nombreColumna;


    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

}
