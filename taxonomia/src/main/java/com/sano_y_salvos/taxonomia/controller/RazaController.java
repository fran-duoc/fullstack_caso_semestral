package com.sano_y_salvos.taxonomia.controller;

import com.sano_y_salvos.taxonomia.model.Especie;
import com.sano_y_salvos.taxonomia.model.Raza;
import com.sano_y_salvos.taxonomia.service.RazaService;
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
@Tag(name = "razas", description = "operaciones relacionadas con las razas")
@RequestMapping("/api/v1/razas")
public class RazaController {
    @Autowired
    private RazaService razaService;

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

    @Operation(summary = "obtiene la informacion de todas las razas")
    @GetMapping
    public CollectionModel<Raza> listar() {
        List<Raza> razas = razaService.getRazas();

        CollectionModel<Raza> model = CollectionModel.of(razas);
        //version automatica
        model.add(
                linkTo(
                        methodOn(RazaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8084/api/v1/razas/{id}",
                        "buscar-razas-por-su-id"
                )
        );

        return model;
    }
    /*@Operation(summary = "obtiene la informacion de todas las razas")
    @GetMapping
    public ResponseEntity<List<Raza>> listar (){
        List<Raza> razas = razaService.getRazas();
        if (razas.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(razas);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una raza por su id")
    @GetMapping("/{id}")
    public EntityModel<Raza> getRaza(@PathVariable Integer id){
        Raza raza = razaService.getRaza(id).orElseThrow();
        EntityModel<Raza> model = EntityModel.of(raza);
        //version automatica
        model.add(
                linkTo(
                        methodOn(RazaController.class)
                                .getRaza(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8084/api/v1/razas",
                        "todas-las-razas"
                )
        );
        return model;
    }
    /*@Operation(summary = "obtiene la informacion de una raza por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Raza> buscarPorId(@PathVariable Integer id){
        Optional<Raza> raza = razaService.getRaza(id);
        if (raza.isPresent())
            return ResponseEntity.ok(raza.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "añadir una raza")
    @PostMapping
    public ResponseEntity<Raza> agregarRaza(@RequestBody Raza raza){
        Raza razaNueva = razaService.saveRaza(raza);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(razaNueva);
    }

    @Operation(summary = "modifica la informacion de una raza por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Raza> editar(@PathVariable Integer id,@RequestBody Raza raza) {
        Optional<Raza> existe = razaService.getRaza(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        raza.setIdRaza(id);
        Raza actualizada = razaService.saveRaza(raza);
        return ResponseEntity.ok(actualizada); //201
    }

    @Operation(summary = "elimina una raza por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            razaService.deleteRaza(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }
}
