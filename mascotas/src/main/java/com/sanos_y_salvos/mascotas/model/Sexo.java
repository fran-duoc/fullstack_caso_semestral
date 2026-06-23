package com.sanos_y_salvos.mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "sexo")
@Data
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Integer idSexo;

    @NotBlank(message = "La descripción del sexo es obligatoria")
    @Size(max = 50, message = "La descricion no puede ser mayor a 50 caracteres")
    @Column(name = "descripcion")
    private String descripcion;
}