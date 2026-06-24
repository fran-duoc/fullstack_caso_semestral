package com.sano_y_salvos.taxonomia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "raza")
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_raza")
    private Integer idRaza;

    @NotBlank(message = "El nombre de la raza no puede estar en blanco.")
    @Max(value = 150, message = "El nombre no puede ser mas de 150 caracteres")
    @Column(name = "nombre_raza")
    private String nombreRaza;

    @ManyToOne
    @JoinColumn(name = "id_especie")
    private Especie especie;


}
