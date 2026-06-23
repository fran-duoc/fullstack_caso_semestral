package com.sanos_y_salvos.alerta.controller;

import com.sanos_y_salvos.alerta.dto.AlertaDetalleDto;
import com.sanos_y_salvos.alerta.model.Alerta;
import com.sanos_y_salvos.alerta.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "alertas", description = "operaciones relacionadas con las alertas")
@RequestMapping("/api/v1/alertas")
public class AlertaController {
    @Autowired
    private AlertaService alertaService;

    @Operation(summary = "obtiene la informacion de todas las alertas")
    @GetMapping
    public CollectionModel<Alerta> listar() {
        List<Alerta> alertas = alertaService.getAlertas();

        CollectionModel<Alerta> model = CollectionModel.of(alertas);
        //version automatica
        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8079/api/v1/alertas/{id}", "buscar-alerta-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8079/api/v1/alertas/{id}/detalle",
                        "buscar-alerta-con-detalles")
        );

        return model;
    }
    //public ResponseEntity<List<Alerta>> listar (){
      //  List<Alerta> alertas = alertaService.getAlertas();
        //if (alertas.isEmpty()){
          //  return ResponseEntity.noContent().build();//204 no content
        //}else{
            //return ResponseEntity.ok(alertas);//200 ok
            // }
            //}
    @Operation(summary = "busca la informacin de una alerta por su id")
    @GetMapping("/{id}")
    public EntityModel<Alerta> getAlerta(@PathVariable Integer id){
        Alerta alerta = alertaService.getAlerta(id).orElseThrow();
        EntityModel<Alerta> model = EntityModel.of(alerta);
        model.add(
                linkTo( methodOn(AlertaController.class).getAlerta(id)
                ).withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8079/api/v1/alertas",
                        "todas-las-alertas")
        );
        model.add(
                Link.of("http://localhost:8079/api/v1/alertas/{id}/detalle",
                        "buscar-alerta-con-detalles")
        );
        return model;
    }
    //public ResponseEntity<Alerta> buscarPorId(@PathVariable Integer id){
      //  Optional<Alerta> mascota = alertaService.getAlerta(id);
        //if (mascota.isPresent())
          //  return ResponseEntity.ok(mascota.get());   // 200 - OK
        //else
          //  return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar una nueva alerta")
    @PostMapping
    public ResponseEntity<Alerta> agregarAlerta(@RequestBody Alerta alerta){
        Alerta mascotaNueva = alertaService.saveAlerta(alerta);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - creado
                .body(mascotaNueva);
    }

    @Operation(summary = "modificar la informacion de una alerta por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Alerta> editar(@PathVariable Integer id,@RequestBody Alerta alerta) {
        Optional<Alerta> existe = alertaService.getAlerta(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        alerta.setIdAlerta(id);
        Alerta actualizada = alertaService.saveAlerta(alerta);
        return ResponseEntity.ok(actualizada); //200
    }
    @Operation(summary = "eliminar una alerta por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            alertaService.deleteAlerta(id);
            return ResponseEntity.noContent().build(); //404
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //204
        }
    }

    //crear la conexion con duenio
    @Operation(summary = "Obtiene la información de una alerta junto a su dueño por ID")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<AlertaDetalleDto>> getDetalleAlerta(
            @PathVariable Integer id) {

        AlertaDetalleDto detalle = alertaService.getAlertaConDuenio(id);

        EntityModel<AlertaDetalleDto> model = EntityModel.of(detalle);

        model.add(
                linkTo(methodOn(AlertaController.class).getDetalleAlerta(id))
                        .withSelfRel()
        );
        model.add(
                linkTo(methodOn(AlertaController.class).getDetalleAlerta(id))
                        .withRel("buscar-alerta-detalle")
        );
        model.add(
                linkTo(methodOn(AlertaController.class).getAlerta(id))
                        .withRel("alerta-base")
        );
        model.add(
                Link.of("http://localhost:8079/api/v1/alertas", "todas-las-alertas")
        );

        return ResponseEntity.ok(model);
    }
}