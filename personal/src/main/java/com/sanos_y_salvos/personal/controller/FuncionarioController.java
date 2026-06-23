package com.sanos_y_salvos.personal.controller;

import com.sanos_y_salvos.personal.dto.PersonalDetalleDTO;
import com.sanos_y_salvos.personal.model.Cargo;
import com.sanos_y_salvos.personal.model.Funcionario;
import com.sanos_y_salvos.personal.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "funcionario", description = "operaciones relacionadas con los funcionarios")
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {
    @Autowired

    private FuncionarioService funcionarioService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public java.util.Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        java.util.Map<String, String> errors = new java.util.HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @Operation(summary = "obtiene la informacion de todas los funcionarios")
    @GetMapping
    public CollectionModel<Funcionario> listar() {
    List<Funcionario> funcionarios = funcionarioService.getFuncionarios();

    CollectionModel<Funcionario> model = CollectionModel.of(funcionarios);
    //version automatica
        model.add(
    linkTo(
            methodOn(FuncionarioController.class)
                                .listar()
                ).withSelfRel()
        );
    //version manual
        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios/{id}", "buscar-funcionarios-por-su-id")
                );
        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios/{id}/institucion", "buscar-funcionario-con-su-institucion")
        );

        return model;
}
   // public ResponseEntity<List<Funcionario>> listar() {
     //   List<Funcionario> funcionarios = funcionarioService.getFuncionarios();
       // if (funcionarios.isEmpty()) {
         //   return ResponseEntity.noContent().build();//204 no content
        //} else {
          //  return ResponseEntity.ok(funcionarios);//201 ok
        //}
    //}

    @Operation(summary = "obtiene la informacion de un funcionario por su id")
    @GetMapping("/{id}")
    public EntityModel<Funcionario> getFuncionario(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.getFuncionario(id).orElseThrow();
        EntityModel<Funcionario> model = EntityModel.of(funcionario);

        //version automatica
        model.add(
                linkTo(
                        methodOn(FuncionarioController.class)
                                .getFuncionario(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios", "todos-los-funcionarios")
        );
        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios/{id}/institucion", "buscar-funcionario-con-su-institucion")
        );
        return model;
    }
   // public ResponseEntity<Funcionario> buscarPorId(@PathVariable Integer id) {
     //   Optional<Funcionario> funcionario = funcionarioService.getFuncionario(id);
       // if (funcionario.isPresent())
         //   return ResponseEntity.ok(funcionario.get());   // 201 - OK
        //else
          //  return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar nuevo funcionario")
    @PostMapping
    public ResponseEntity<Funcionario> agregarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioNueva = funcionarioService.saveFuncionario(funcionario);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(funcionarioNueva);
    }

    @Operation(summary = "modificar un funcionario por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> editar(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> existe = funcionarioService.getFuncionario(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setIdFuncionario(id);

        Funcionario actualizada = funcionarioService.saveFuncionario(funcionario);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "eliminar un funcionario por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            funcionarioService.deleteFuncionario(id);
            return ResponseEntity.noContent().build(); //204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); //404
        }
    }

    //conexion de funcionario con institucion
    @Operation(summary = "Obtiene la información de un funcionario junto a su institución por ID")
    @GetMapping("/{id}/institucion")
    public ResponseEntity<EntityModel<PersonalDetalleDTO>> obtenerPersonalConInstitucion(
            @PathVariable Integer id) {

        PersonalDetalleDTO detalle = funcionarioService.getPersonalConInsti(id);

        EntityModel<PersonalDetalleDTO> model = EntityModel.of(detalle);

        model.add(
                linkTo(methodOn(FuncionarioController.class).obtenerPersonalConInstitucion(id))
                        .withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios", "todos-los-funcionarios")
        );
        model.add(
                Link.of("http://localhost:8086/api/v1/funcionarios/{id}", "buscar-funcionario-por-su-id")
        );

        return ResponseEntity.ok(model);
    }
}