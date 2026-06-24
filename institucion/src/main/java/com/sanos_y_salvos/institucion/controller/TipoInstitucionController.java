package com.sanos_y_salvos.institucion.controller;

import com.sanos_y_salvos.institucion.model.Institucion;
import com.sanos_y_salvos.institucion.model.TipoInstitucion;
import com.sanos_y_salvos.institucion.service.TipoInstitucionService;
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
@Tag(name = "tipo de institucion", description = "operaciones relacionadas con el tipo de instituciones")
@RequestMapping("/api/v1/tipo")
public class TipoInstitucionController {
    @Autowired
    private TipoInstitucionService tipoInstitucionService;

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

    @Operation(summary = "obtiene la informacion de todos los tipos de instituciones")
    @GetMapping
    public CollectionModel<TipoInstitucion> listar() {
        List<TipoInstitucion> tipoInstitucions = tipoInstitucionService.getTipoInstitucions();

        CollectionModel<TipoInstitucion> model = CollectionModel.of(tipoInstitucions);
        //version automatica
        model.add(
                linkTo(
                        methodOn(TipoInstitucionController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8083/api/v1/tipo/{id}",
                        "buscar-tipo-por-su-id")
        );
        return model;
    }

    /*public ResponseEntity<List<TipoInstitucion>> listar (){
        List<TipoInstitucion> tipoInstitucions = tipoInstitucionService.getTipoInstitucions();
        if (tipoInstitucions.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(tipoInstitucions);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de un tipo de institucion por su id")
    @GetMapping("/{id}")
    public EntityModel<TipoInstitucion> getTipoInstitucion(@PathVariable Integer id) {
        TipoInstitucion tipoInstitucion = tipoInstitucionService.getTipoInstitucion(id).orElseThrow();
        EntityModel<TipoInstitucion> model = EntityModel.of(tipoInstitucion);
        //version automatica
        model.add(
                linkTo(
                        methodOn(TipoInstitucionController.class)
                                .getTipoInstitucion(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8083/api/v1/tipo",
                        "todos-los-tipos"
                )
        );
        return model;
    }
    /*public ResponseEntity<TipoInstitucion> buscarPorId(@PathVariable Integer id){
        Optional<TipoInstitucion> tipoInstitucion = tipoInstitucionService.getTipoInstitucion(id);
        if (tipoInstitucion.isPresent())
            return ResponseEntity.ok(tipoInstitucion.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "agrega un nuevo tipo de institucion")
    @PostMapping
    public ResponseEntity<TipoInstitucion> agregarTipoInstitucion(@RequestBody TipoInstitucion tipoInstitucion){
        TipoInstitucion tipoInstitucionNueva = tipoInstitucionService.saveTipoInstitucion(tipoInstitucion);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(tipoInstitucionNueva);
    }

    @Operation(summary = "modifica un tipo de institucion por su id")
    @PutMapping("/{id}")
    public ResponseEntity<TipoInstitucion> editar(@PathVariable Integer id,@RequestBody TipoInstitucion tipoInstitucion) {
        Optional<TipoInstitucion> existe = tipoInstitucionService.getTipoInstitucion(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        tipoInstitucion.setIdTipo(id);
        TipoInstitucion actualizada = tipoInstitucionService.saveTipoInstitucion(tipoInstitucion);
        return ResponseEntity.ok(actualizada); //201
    }

    @Operation(summary = "elimina un tipo de institucion por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            tipoInstitucionService.deleteTipoInstitucion(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //201
        }
    }

}
