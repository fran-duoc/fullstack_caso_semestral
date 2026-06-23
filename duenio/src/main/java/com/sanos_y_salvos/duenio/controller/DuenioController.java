package com.sanos_y_salvos.duenio.controller;

import com.sanos_y_salvos.duenio.dto.DuenioDetalleDTO;
import com.sanos_y_salvos.duenio.model.Duenio;
import com.sanos_y_salvos.duenio.service.DuenioService;
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
@Tag(name = "dueños", description = "operaciones relacionadas con los dueños")
@RequestMapping("/api/v1/duenios")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

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

    @Operation(summary = "obtiene la informacion de todos los dueños")
    @GetMapping
    public CollectionModel<Duenio> listar() {
        List<Duenio> duenios = duenioService.getDuenios();

        CollectionModel<Duenio> model = CollectionModel.of(duenios);
        //version automatica
        model.add(
                linkTo(
                        methodOn(DuenioController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8090/api/v1/duenios/{id}",
                        "buscar-duenio-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8090/api/v1/duenios/{id}/detalle",
                        "buscar-duenios-con-su-comuna")
        );
        return model;
    }
    /*public ResponseEntity<List<Duenio>> listar (){
        List<Duenio> duenios = duenioService.getDuenios();
        if (duenios.isEmpty()){
            return ResponseEntity.noContent().build();//204 no content
        }else{
            return ResponseEntity.ok(duenios);//201 ok
        }*/

    @Operation(summary = "obtiene la informacion de un dueño por su id")
    @GetMapping("/{id}")
    public EntityModel<Duenio> getDuenio(@PathVariable Integer id) {
        Duenio duenio = duenioService.getDuenio(id).orElseThrow();
        EntityModel<Duenio> model = EntityModel.of(duenio);
        //version automatica
        model.add(
                linkTo(
                        methodOn(DuenioController.class)
                                .getDuenio(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8090/api/v1/duenios",
                        "todos-los-duenios"
                )
        );
        model.add(
                Link.of("http://localhost:8090/api/v1/duenios/{id}/detalle",
                        "mostrar-duenio-con-su-comuna")
        );
        return model;
    }



    /*public ResponseEntity<Duenio> buscarPorId(@PathVariable Integer id){
        Optional<Duenio> duenio = duenioService.getDuenio(id);
        if (duenio.isPresent())
            return ResponseEntity.ok(duenio.get());   // 201 - OK
        else
            return ResponseEntity.notFound().build();   // 404 - Not Found
    }
*/
    @Operation(summary = "agregar un nuevo dueño")
    @PostMapping
    public ResponseEntity<Duenio> agregarDuenio(@RequestBody Duenio duenio){
        Duenio duenioNuevo = duenioService.saveDuenio(duenio);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(duenioNuevo);
    }

    @Operation(summary = "modificar informacion de dueño por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Duenio> editar(@RequestBody Duenio duenio, @PathVariable Integer id) {
        Optional<Duenio> existe = duenioService.getDuenio(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        duenio.setIdDuenio(id);
        Duenio actualizada = duenioService.saveDuenio(duenio);
        return ResponseEntity.ok(actualizada); //200
    }

    @Operation(summary = "eliminar un dueño por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            duenioService.deleteDuenio(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //404
        }
    }




    @Operation(summary = "obtiene la informacion de dueño junto a su comuna")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<DuenioDetalleDTO>> listarDetalles(@PathVariable Integer id) {
        DuenioDetalleDTO detalle = duenioService.getDuenioConComuna(id);

        EntityModel<DuenioDetalleDTO> model = EntityModel.of(detalle);

        //version automatica
        model.add(
                linkTo(methodOn(DuenioController.class).listarDetalles(id)).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8090/api/v1/duenio/{id}",
                        "buscar-duenio-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8090/api/v1/duenio",
                        "todos-los-duenios")
        );

        return ResponseEntity.ok(model);
    }
    //public DuenioDetalleDTO getDuenioConComuna() {
    //  return duenioService.getDuenioConComuna();
    //}
}