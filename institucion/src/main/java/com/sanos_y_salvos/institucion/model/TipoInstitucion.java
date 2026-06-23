package com.sanos_y_salvos.institucion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tipo_institucion")
public class TipoInstitucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_tipo")
    private Integer idTipo;
    @NotBlank(message = "La descripción no puede estar en blanco.")
    private String descripcion;

}