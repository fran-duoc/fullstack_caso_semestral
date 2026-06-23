package com.sanos_y_salvos.localizacion.controller;

import com.sanos_y_salvos.localizacion.model.Region;
import com.sanos_y_salvos.localizacion.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@Tag(name = "regiones", description = "operaciones relacionadas con las regiones")
@RequestMapping("api/v1/regiones")
public class RegionController {
    @Autowired
    private RegionService regionService;

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

    @Operation(summary = "obtiene la informacion de todas las regiones")
    @GetMapping
    public CollectionModel<Region> listar() {
        List<Region> regions = regionService.getRegion();

        CollectionModel<Region> model = CollectionModel.of(regions);
        //version automatica
        model.add(
                linkTo(
                        methodOn(RegionController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8088/api/v1/regiones/{id}",
                        "buscar-regiones-por-su-id"
                )
        );

        return model;
    }
    /*@Operation(summary = "obtiene la informacion de todas las regiones")
    @GetMapping
    public ResponseEntity<List<Region>> listar (){
        List<Region> regions = regionService.getRegion();
        if (regions.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(regions);//201 ok
        }
    }*/

    @Operation(summary = "obtiene la informacion de una region por su id")
    @GetMapping("{id}")
    public EntityModel<Region> getRegion(@PathVariable Integer id){
        Region region = regionService.getRegion(id).orElseThrow();
        EntityModel<Region> model = EntityModel.of(region);
        //version automatica
        model.add(
                linkTo(
                        methodOn(RegionController.class)
                                .getRegion(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8088/api/v1/regiones",
                        "todas-las-regiones"
                )
        );
        return model;
    }
    /*@Operation(summary = "obtiene la informacion de una region por su id")
    @GetMapping("{id}")
    public ResponseEntity<Region> buscarPorId(@PathVariable Integer id){
        Optional<Region> region = regionService.getRegion(id);
        if (region.isPresent())
            return ResponseEntity.ok(region.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }*/

    @Operation(summary = "agrega una region")
    @PostMapping
    public ResponseEntity<Region> agregarRegion(@RequestBody Region region){
        Region regionNueva = regionService.saveRegion(region);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(regionNueva);
    }

    @Operation(summary = "modifica la informacion de una region por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Region> editar(@PathVariable Integer id,@RequestBody Region region) {
        Optional<Region> existe = regionService.getRegion(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        region.setIdRegion(id);

        Region actualizada = regionService.saveRegion(region);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "elimina una region por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            regionService.deleteRegion(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
