package com.sanos_y_salvos.institucion.controller;

import com.sanos_y_salvos.institucion.model.Institucion;
import com.sanos_y_salvos.institucion.service.InstitucionService;
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
@Tag(name = "institucion", description = "operaciones relacionadas con las instituciones")
@RequestMapping("/api/v1/institucion")
public class InstitucionController {
    @Autowired
    private InstitucionService institucionService;

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

    @Operation(summary = "obtiene la informacion de todas las instituciones")
    @GetMapping
    public CollectionModel<Institucion> listar() {
        List<Institucion> institucions = institucionService.getInstitucions();

        CollectionModel<Institucion> model = CollectionModel.of(institucions);
        //version automatica
        model.add(
                linkTo(
                        methodOn(InstitucionController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8083/api/v1/institucion/{id}", "buscar-institucion-por-su-id")
        );
        return model;
    }

    /*public ResponseEntity<List<Institucion>> listar (){
        List<Institucion> institucions = institucionService.getInstitucions();
        if (institucions.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(institucions);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una institucion por su id")
    @GetMapping("/{id}")
    public EntityModel<Institucion> getInstitucion(@PathVariable Integer id) {
        Institucion institucion = institucionService.getInstitucion(id).orElseThrow();
        EntityModel<Institucion> model = EntityModel.of(institucion);
        //version automatica
        model.add(
                linkTo(
                        methodOn(InstitucionController.class)
                                .getInstitucion(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8083/api/v1/institucion",
                        "todas-las-instituciones"
                )
        );
        return model;
    }




    /*public ResponseEntity<Institucion> buscarPorId(@PathVariable Integer id){
        Optional<Institucion> institucion = institucionService.getInstitucion(id);
        if (institucion.isPresent())
            return ResponseEntity.ok(institucion.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "agregar nueva institucion")
    @PostMapping
    public ResponseEntity<Institucion> agregarInstitucion(@RequestBody Institucion institucion){
        Institucion institucionNueva = institucionService.saveInstitucion(institucion);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(institucionNueva);
    }

    @Operation(summary = "modificar una institucion por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Institucion> editar(@PathVariable Integer id,@RequestBody Institucion institucion) {
        Optional<Institucion> existe = institucionService.getInstitucion(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        institucion.setIdInstitucion(id);

        Institucion actualizada = institucionService.saveInstitucion(institucion);
        return ResponseEntity.ok(actualizada); //201
    }
    @Operation(summary = "eliminar institucion por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            institucionService.deleteInsitucion(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }
}
