package com.sanos_y_salvos.localizacion.controller;

import com.sanos_y_salvos.localizacion.model.Comuna;
import com.sanos_y_salvos.localizacion.service.ComunaService;
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
@Tag(name = "comunas", description = "operaciones relacionadas con las comunas")
@RequestMapping("/api/v1/comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

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

    @Operation(summary = "obtiene la informacion de todas las comunas")
    @GetMapping
    public CollectionModel<Comuna> listar() {
        List<Comuna> comunas = comunaService.getComunas();

        CollectionModel<Comuna> model = CollectionModel.of(comunas);
        //version automatica
        model.add(
                linkTo(
                        methodOn(ComunaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8088/api/v1/comunas/{id}",
                        "buscar-comuna-por-su-id"
                )
        );

        return model;
    }
    /*@Operation(summary = "obtiene la informacion de todas las comunas")
    @GetMapping
    public ResponseEntity<List<Comuna>> listar (){
        List<Comuna> comunas = comunaService.getComunas();
        if (comunas.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(comunas);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una comuna por su id")
    @GetMapping("/{id}")
    public EntityModel<Comuna> getComuna(@PathVariable Integer id){
        Comuna comuna = comunaService.getComuna(id).orElseThrow();
        EntityModel<Comuna> model = EntityModel.of(comuna);
        //version automatica
        model.add(
                linkTo(
                        methodOn(ComunaController.class)
                                .getComuna(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8088/api/v1/comunas",
                        "todas-las-comunas"
                )
        );
        return model;
    }
    /*@Operation(summary = "obtiene la informacion de una comuna por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> buscarPorId(@PathVariable Integer id){
        Optional<Comuna> comuna = comunaService.getComuna(id);
        if (comuna.isPresent())
            return ResponseEntity.ok(comuna.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "agrega una comuna")
    @PostMapping
    public ResponseEntity<Comuna> agregarComuna(@RequestBody Comuna comuna){
        Comuna comunaNueva = comunaService.saveComuna(comuna);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(comunaNueva);
    }

    @Operation(summary = "modifica la informacion de una comuna por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Comuna> editar(@RequestBody Comuna comuna, @PathVariable Integer id) {
        Optional<Comuna> existe = comunaService.getComuna(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        comuna.setIdComuna(id);
        Comuna actualizada = comunaService.saveComuna(comuna);
        return ResponseEntity.ok(actualizada); //201
    }

    @Operation(summary = "elimina una comuna por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            comunaService.deleteComuna(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }
}