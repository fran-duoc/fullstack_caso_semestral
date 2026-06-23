package com.sanos_y_salvos.localizacion.controller;

import com.sanos_y_salvos.localizacion.model.Comuna;
import com.sanos_y_salvos.localizacion.model.Provincia;
import com.sanos_y_salvos.localizacion.service.ProvinciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "provincias", description = "operaciones relacionadas con las provincias")
@RequestMapping("/api/v1/provincias")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;

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

    @Operation(summary = "obtiene la informacion de todas las provincias")
    @GetMapping
    public CollectionModel<Provincia> listar() {
        List<Provincia> provincias = provinciaService.getProvincias();

        CollectionModel<Provincia> model = CollectionModel.of(provincias);
        //version automatica
        model.add(
                linkTo(
                        methodOn(ProvinciaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8088/api/v1/provincias/{id}",
                        "buscar-provincia-por-su-id"
                )
        );

        return model;
    }
    /*@Operation(summary = "obtiene la informacion de todas las provincias")
    @GetMapping
    public ResponseEntity<List<Provincia>> listar (){
        List<Provincia> provincias = provinciaService.getProvincias();
        if (provincias.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(provincias);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una provincia por su id")
    @GetMapping("/{id}")
    public EntityModel<Provincia> getProvincia(@PathVariable Integer id){
        Provincia provincia = provinciaService.getProvincia(id).orElseThrow();
        EntityModel<Provincia> model = EntityModel.of(provincia);
        //version automatica
        model.add(
                linkTo(
                        methodOn(ProvinciaController.class)
                                .getProvincia(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8088/api/v1/provincias",
                        "todas-las-provincias"
                )
        );
        return model;
    }
    /*@Operation(summary = "obtiene la informacion de una provincia por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> buscarPorId(@PathVariable Integer id){
        Optional<Provincia> provincia = provinciaService.getProvincia(id);
        if (provincia.isPresent())
            return ResponseEntity.ok(provincia.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "agregar una provincia")
    @PostMapping
    public ResponseEntity<Provincia> agregarProvincia(@RequestBody Provincia provincia){
        Provincia provinciaNueva = provinciaService.saveProvincia(provincia);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(provinciaNueva);
    }

    @Operation(summary = "modifica la informacion de una provincia por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> editar(@RequestBody Provincia provincia, @PathVariable Integer id) {
        Optional<Provincia> existe = provinciaService.getProvincia(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        provincia.setIdProvincia(id);

        Provincia actualizada = provinciaService.saveProvincia(provincia);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "elimina una provincia por su id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            provinciaService.deleteProvincia(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
