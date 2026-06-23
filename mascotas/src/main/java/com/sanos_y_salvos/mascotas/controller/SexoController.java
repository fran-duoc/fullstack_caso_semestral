package com.sanos_y_salvos.mascotas.controller;

import com.sanos_y_salvos.mascotas.model.Mascota;
import com.sanos_y_salvos.mascotas.model.Sexo;
import com.sanos_y_salvos.mascotas.service.SexoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "sexos", description = "operaciones relacionadas con los sexos")
@RequestMapping("/api/v1/sexos")
public class SexoController {
    @Autowired
    private  SexoService sexoService;

    @Operation(summary = "obtiene la informacion de todos los sexos")
    @GetMapping
    public CollectionModel<Sexo> listar() {
        List<Sexo> sexos = sexoService.getSexos();

        CollectionModel<Sexo> model = CollectionModel.of(sexos);
        //version automatica
        model.add(
                linkTo(
                        methodOn(SexoController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8085/api/v1/sexos/{id}",
                        "buscar-sexos-mascotas-por-su-id"
                )
        );

        return model;
    }

    //@Operation(summary = "obtiene la informacion de todos los sexos")
    //@GetMapping
    //public ResponseEntity<List<Sexo>> listar (){
    //    List<Sexo> sexo = sexoService.getSexos();
    //    if (sexo.isEmpty()){
    //        return ResponseEntity.noContent().build();//204 no content
    //    }else{
    //        return ResponseEntity.ok(sexo);//201 ok
    //    }
    //}

    @Operation(summary = "buscar la informacio de un sexo por su id")
    @GetMapping("/{id}")
    public EntityModel<Sexo> getSexo(@PathVariable Integer id){
        Sexo sexo = sexoService.getSexo(id).orElseThrow();
        EntityModel<Sexo> model = EntityModel.of(sexo);
        //version automatica
        model.add(
                linkTo(
                        methodOn(SexoController.class)
                                .getSexo(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8085/api/v1/sexos",
                        "todos-los-sexos-de-mascotas"
                )
        );
        return model;
    }
    //@Operation(summary = "buscar la informacio de un sexo por su id")
    //@GetMapping("/{id}")
    //public ResponseEntity<Sexo> buscarPorId(@PathVariable Integer id){
    //    Optional<Sexo> sexo = sexoService.getSexo(id);
    //    if (sexo.isPresent())
    //        return ResponseEntity.ok(sexo.get());   // 201 - OK
    //    else
    //        return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar un nuevo sexo")
    @PostMapping
    public ResponseEntity<Sexo> agregarSexo(@RequestBody Sexo sexo){
        Sexo sexoNueva = sexoService.saveSexo(sexo);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(sexoNueva);
    }

    @Operation(summary = "editar la informacion de un sexo por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Sexo> editar(@PathVariable Integer id,@RequestBody Sexo sexo) {
        Optional<Sexo> existe = sexoService.getSexo(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        sexo.setIdSexo(id);
        Sexo actualizada = sexoService.saveSexo(sexo);
        return ResponseEntity.ok(actualizada); //201
    }

    @Operation(summary = "eliminar un sexo por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            sexoService.deleteSexo(id);
            return ResponseEntity.noContent().build(); //404
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //204
        }
    }

}