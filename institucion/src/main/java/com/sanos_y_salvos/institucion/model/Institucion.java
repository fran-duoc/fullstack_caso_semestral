package com.sanos_y_salvos.institucion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "institucion")
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @Column(name = "rut_institucion")
    private String rutInstitucion;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;

    @NotNull(message = "El teléfono no puede estar vacío")
    @Max(value = 999999999, message = "El teléfono no puede tener más de 9 caracteres")
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoInstitucion tipoInstitucion;
}
