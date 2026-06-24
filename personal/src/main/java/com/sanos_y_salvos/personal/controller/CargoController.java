package com.sanos_y_salvos.personal.controller;

import com.sanos_y_salvos.personal.model.Cargo;
import com.sanos_y_salvos.personal.service.CargoService;
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
@Tag(name = "cargo", description = "operaciones relacionadas con los cargos")
@RequestMapping("/api/v1/cargos")
public class CargoController {
    @Autowired
    private CargoService cargoService;

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

    @Operation(summary = "obtiene la informacion de todos los cargos")
    @GetMapping
    public CollectionModel<Cargo> listar() {
        List<Cargo> cargos = cargoService.getCargos();

        CollectionModel<Cargo> model = CollectionModel.of(cargos);
        //version automatica
        model.add(
                linkTo(
                        methodOn(CargoController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8086//api/v1/cargos/{id}",
                        "buscar-cargo-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8086/api/v1/cargos",
                        "todos-los-cargos")
        );

        return model;
    }
    //public ResponseEntity<List<Cargo>> listar (){
      //  List<Cargo> cargos = cargoService.getCargos();
        //if (cargos.isEmpty()){
          //  return ResponseEntity.noContent().build();//204 no content
        //}else{
          //  return ResponseEntity.ok(cargos);//201 ok
       // }
    //}
    @Operation(summary = "obtiene la informacion de un cargo por su id")
    @GetMapping("/{id}")
    public EntityModel<Cargo> getCargo(@PathVariable Integer id){
        Cargo cargo = cargoService.getCargo(id).orElseThrow();
        EntityModel<Cargo> model = EntityModel.of(cargo);

        //version automatica
        model.add(
                linkTo(
                        methodOn(CargoController.class)
                                .getCargo(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8086//api/v1/cargos/{id}",
                        "buscar-cargo-por-su-id")
        );
        model.add(
                Link.of(
                        "http://localhost:8086/api/v1/cargos",
                        "todos-los-cargos"
                )
        );
        return model;
    }
    //public ResponseEntity<Cargo> buscarPorId(@PathVariable Integer id){
      //  Optional<Cargo> cargo = cargoService.getCargo(id);
        //if (cargo.isPresent())
          //  return ResponseEntity.ok(cargo.get());   // 201 - OK
        //else
          //  return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar un nuevo cargo")
    @PostMapping
    public ResponseEntity<Cargo> agregarCargo(@RequestBody Cargo cargo){
        Cargo cargoNuevo = cargoService.saveCargo(cargo);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(cargoNuevo);
    }

    @Operation(summary = "modificar un cargo por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Cargo> editar(@PathVariable Integer id,@RequestBody Cargo cargo) {
        Optional<Cargo> existe = cargoService.getCargo(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        cargo.setIdCargo(id);

        Cargo actualizada = cargoService.saveCargo(cargo);
        return ResponseEntity.ok(actualizada); //201
    }
    @Operation(summary = "eliminar un cargo por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            cargoService.deleteCargo(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }
}