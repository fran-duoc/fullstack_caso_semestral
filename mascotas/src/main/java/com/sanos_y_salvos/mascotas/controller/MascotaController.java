package com.sanos_y_salvos.mascotas.controller;

import com.sanos_y_salvos.mascotas.dto.MascotaDetalleDTO;
import com.sanos_y_salvos.mascotas.model.Mascota;
import com.sanos_y_salvos.mascotas.service.MascotaService;
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

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "mascotas", description = "operaciones relacionadas con las mascotas")
@RequestMapping("/api/v1/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

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

    @Operation(summary = "obtiene la informacion de todas las mascotas")
    @GetMapping
    public CollectionModel<Mascota> listar() {
        List<Mascota> mascotas = mascotaService.getMascotas();

        CollectionModel<Mascota> model = CollectionModel.of(mascotas);
        //version automatica
        model.add(
                linkTo(
                        methodOn(MascotaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8085/api/v1/mascotas/{id}",
                        "buscar-mascota-por-su-id")
        );
        model.add(
                Link.of(
                        "http://localhost:8085/api/v1/mascotas/{id}/detalle",
                        "mascota-con-duenio-raza"
                )
        );

        return model;
    }
    //@Operation(summary = "obtiene la informacion de todas las mascotas")
    //@GetMapping
    //public ResponseEntity<List<Mascota>> listar (){
    //    List<Mascota> mascotas = mascotaService.getMascotas();
    //    if (mascotas.isEmpty()){
    //        return ResponseEntity.noContent().build();//204 no content
    //    }else{
    //        return ResponseEntity.ok(mascotas);//201 ok
    //    }
    //}

    @Operation(summary = "busca la informacion de mascota por su id")
    @GetMapping("/{id}")
    public EntityModel<Mascota> getMascota(@PathVariable Integer id){
        Mascota mascota = mascotaService.getMascota(id).orElseThrow();
        EntityModel<Mascota> model = EntityModel.of(mascota);

        //version automatica
        model.add(
            linkTo(
                    methodOn(MascotaController.class)
                    .getMascota(id)
            ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8085/api/v1/mascotas",
                        "todas-las-mascotas"
                )
        );
        model.add(
                Link.of(
                        "http://localhost:8085/api/v1/mascotas/{id}/detalle",
                        "mascota-con-duenio-raza"
                )
        );
        return model;
    }
    //@Operation(summary = "busca la informacin de una mascota por su id")
    //@GetMapping("/{id}")
    //public ResponseEntity<Mascota> buscarPorId(@PathVariable Integer id){
    //    Optional<Mascota> mascota = mascotaService.getMascota(id);
    //    if (mascota.isPresent())
    //        return ResponseEntity.ok(mascota.get());   // 201 - OK
    //    else
    //        return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar una nueva mascota")
    @PostMapping
    public ResponseEntity<Mascota> agregarMascota(@RequestBody Mascota mascota){
        Mascota  mascotaNueva = mascotaService.saveMascota(mascota);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(mascotaNueva);
    }

    @Operation(summary = "modificar la informacion de una mascota por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> editar(@PathVariable Integer id,@RequestBody Mascota mascota) {
        Optional<Mascota> existe = mascotaService.getMascota(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        mascota.setIdMascota(id);
        Mascota actualizada = mascotaService.saveMascota(mascota);
        return ResponseEntity.ok(actualizada); //201
    }
    @Operation(summary = "eliminar una mascota por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            mascotaService.deleteMascota(id);
            return ResponseEntity.noContent().build(); //404
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //204
        }
    }

    @Operation(summary = "mascotas con duenio y raza")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<MascotaDetalleDTO>> listarDetalles(@PathVariable Integer id) {

        MascotaDetalleDTO detalle = mascotaService.getMascotaDetalle(id);

        EntityModel<MascotaDetalleDTO> model = EntityModel.of(detalle);

        // 3. Link autom
        model.add(
                linkTo(methodOn(MascotaController.class).listarDetalles(id)).withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8085/api/v1/mascotas/{id}",
                        "buscar-mascota-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8085/api/v1/mascotas",
                        "todas-las-mascotas")
        );

        return ResponseEntity.ok(model);
    }
    //@Operation(summary = "conexion entre mascotas y dueño")
    //@GetMapping("/detalles")
    //public MascotaDetalleDTO getMascotasDetalle(){
    //    return mascotaService.getMascotaDetalle();
    //}
}