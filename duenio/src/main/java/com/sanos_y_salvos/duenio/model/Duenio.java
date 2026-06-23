package com.sanos_y_salvos.duenio.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "duenio")
@Data
@JsonPropertyOrder({
        "idDuenio",
        "run",
        "dvrun",
        "pNombre",
        "sNombre",
        "apPaterno",
        "apMaterno",
        "email",
        "telefono",
        "idComuna"
})
public class Duenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duenio")
    private Integer idDuenio;

    @NotBlank(message = "El RUN es obligatorio")
    @Size(max = 8, message = "El RUN no puede superar los 8 caracteres")
    private String run;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(max = 1, message = "El DV debe ser de 1 carácter")
    private String dvrun;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Column(name = "p_nombre")
    private String pNombre;

    @Column(name = "s_nombre")
    private String sNombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Column(name = "ap_paterno")
    private String apPaterno;

    @Column(name = "ap_materno")
    private String apMaterno;

    @NotNull(message = "El teléfono es obligatorio")
    @Max(value = 999999999, message = "El teléfono no puede tener más de 9 números")
    @Column(name = "fono")
    private Integer telefono;

    @Email(message = "Debe ingresar un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "El ID de la comuna es obligatorio")
    @Column(name = "id_comuna")
    private Integer idComuna;
}
