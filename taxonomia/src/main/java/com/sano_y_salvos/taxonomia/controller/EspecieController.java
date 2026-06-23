package com.sano_y_salvos.taxonomia.controller;

import com.sano_y_salvos.taxonomia.model.Especie;
import com.sano_y_salvos.taxonomia.service.EspecieService;
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
@Tag(name = "especies", description = "operaciones relacionadas con las especies")
@RequestMapping("/api/v1/especies")
public class EspecieController {
    @Autowired
    private EspecieService especieService;

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

    @Operation(summary = "obtiene la informacion de todas las especies")
    @GetMapping
    public CollectionModel<Especie> listar() {
        List<Especie> especies = especieService.getEspecies();

        CollectionModel<Especie> model = CollectionModel.of(especies);
        //version automatica
        model.add(
                linkTo(
                        methodOn(EspecieController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8084/api/v1/especies/{id}",
                        "buscar-especies-por-su-id"
                )
        );

        return model;
    }
    /*@Operation(summary = "obtiene la informacion de todas las especies")
    @GetMapping
    public ResponseEntity<List<Especie>> listar (){
        List<Especie> especies = especieService.getEspecies();
        if (especies.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(especies);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una especie por su id")
    @GetMapping("/{id}")
    public EntityModel<Especie> getEspecie(@PathVariable Integer id){
        Especie especie = especieService.getEspecie(id).orElseThrow();
        EntityModel<Especie> model = EntityModel.of(especie);
        //version automatica
        model.add(
                linkTo(
                        methodOn(EspecieController.class)
                                .getEspecie(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8084/api/v1/especies",
                        "todas-las-especies"
                )
        );
        return model;
    }
    /*@Operation(summary = "obtiene la informacion de una especie por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Especie> buscarPorId(@PathVariable Integer id){
        Optional<Especie> especie = especieService.getEspecie(id);
        if (especie.isPresent())
            return ResponseEntity.ok(especie.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "añadir una nueva especie")
    @PostMapping
    public ResponseEntity<Especie> agregarEspecie(@RequestBody Especie especie){
        Especie especieNueva = especieService.saveEspecie(especie);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(especieNueva);
    }

    @Operation(summary = "modifica la informacio de una especie por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Especie> editar(@PathVariable Integer id,@RequestBody Especie especie) {
        Optional<Especie> existe = especieService.getEspecie(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        especie.setIdEspecie(id);
        Especie actualizada = especieService.saveEspecie(especie);
        return ResponseEntity.ok(actualizada); //201
    }

    @Operation(summary = "elimina una especie por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            especieService.deleteEspecie(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }
}
