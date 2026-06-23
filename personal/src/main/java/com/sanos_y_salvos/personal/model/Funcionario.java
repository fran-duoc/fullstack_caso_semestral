package com.sanos_y_salvos.personal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    @NotNull(message = "El RUN es obligatorio")
    @Max(value = 99999999, message = "El run no puede tener más de 8 digítos")
    private Integer run;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(max = 1, message = "El DV debe ser de 1 carácter")
    private String dvrun;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 50, message = "El primer nombre no puede superar los 50 caracteres")
    @Column(name = "p_nombre")
    private String pNombre;

    @NotBlank(message = "El segundo nombre es obligatorio")
    @Size(max = 50, message = "El segundo nombre no puede superar los 50 caracteres")
    @Column(name = "s_nombre")
    private String sNombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 50, message = "El apellido paterno no puede superar los 50 caracteres")
    @Column(name = "ap_paterno")
    private String apPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 50, message = "El apellido materno no puede superar los 50 caracteres")
    @Column(name = "ap_materno")
    private String apMaterno;

    @NotNull(message = "El teléfono es obligatorio")
    @Max(value = 999999999, message = "El teléfono no puede tener más de 9 números")
    private Integer telefono;

    @Email(message = "Debe ingresar un email válido")
    @NotBlank(message = "El email es obligatorio")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;

    @NotBlank(message = "La sede es obligatoria")
    @Size(max = 100, message = "La sede no puede superar los 100 caracteres")
    private String sede;

    @NotNull(message = "El ID de la institución es obligatorio")
    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;
}