package com.sanos_y_salvos.mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "mascota")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer idMascota;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(name = "nombre_mascota")
    private String nombreMascota;

    @NotNull(message = "El ID de la raza es obligatorio")
    @Column(name = "id_raza")
    private Integer idRaza;

    @NotBlank(message = "El color es obligatorio")
    @Size(max = 50, message = "El color no puede superar los 50 caracteres")
    @Column(name = "color")
    private String color;

    @NotNull(message = "El número de chip es obligatorio")
    @Column(name = "chip")
    private Integer chip;

    @NotNull(message = "El ID del dueño es obligatorio")
    @Column(name = "id_duenio")
    private Integer idDuenio;

    @NotNull(message = "La edad es obligatoria")
    @Max(value = 200, message = "La edad no puede ser mayor a 200 años")
    @Column(name = "edad")
    private Integer edad;

    @NotBlank(message = "El diagnóstico o estado actual es obligatorio")
    @Size(max = 255, message = "El diagnóstico no puede superar los 255 caracteres")
    @Column(name = "diagnostico")
    private String diagnostico;

    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;
}