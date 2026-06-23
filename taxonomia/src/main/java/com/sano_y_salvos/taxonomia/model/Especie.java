package com.sano_y_salvos.taxonomia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name = "especie")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_especie")
    private Integer idEspecie;

    @NotBlank(message = "El nombre de la especie no puede estar en blanco.")
    @Max(value = 150, message = "El nombre no puede ser mas de 150 caracteres")
    @Column(name = "nombre_especie")
    private String nombreEspecie;
}
