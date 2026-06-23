package com.sanos_y_salvos.auditoria.controller;

import com.sanos_y_salvos.auditoria.dto.AuditoriaDetalleDto;
import com.sanos_y_salvos.auditoria.model.Auditoria;
import com.sanos_y_salvos.auditoria.service.AuditoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "auditorias", description = "operaciones relacionadas con las auditorias")
@RequestMapping("/api/v1/auditorias")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @Operation(summary = "obtiene la informacion de todas las auditorias")
    @GetMapping
    public CollectionModel<Auditoria> listar() {
        List<Auditoria> auditorias = auditoriaService.getAuditorias();

        CollectionModel<Auditoria> model = CollectionModel.of(auditorias);

        //version automatica
        model.add(
                linkTo(methodOn(AuditoriaController.class).listar()).withSelfRel()
        );

        //version manual
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias/{id}",
                        "buscar-auditoria-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias/{id}/detalle",
                        "buscar-auditoria-con-comuna")
        );

        return model;
    }

    @Operation(summary = "Obtiene la informacion de auditoria por su id")
    @GetMapping("/{id}")
    public EntityModel<Auditoria> getAuditoria(@PathVariable Integer id){
        Auditoria auditoria = auditoriaService.getAuditoria(id).orElseThrow();
        EntityModel<Auditoria> model = EntityModel.of(auditoria);

        //version automatica
        model.add(
                linkTo(methodOn(AuditoriaController.class).getAuditoria(id)).withSelfRel()
        );

        //version manual
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias",
                        "todas-las-auditorias")
        );
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias/{id}/detalle",
                        "buscar-auditoria-con-comuna")
        );
        return model;
    }

    @Operation(summary = "agregar nueva auditoria")
    @PostMapping
    public ResponseEntity<Auditoria> agregarAuditoria(@RequestBody Auditoria auditoria){
        Auditoria nuevaAuditoria = auditoriaService.saveAuditoria(auditoria);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - creado
                .body(nuevaAuditoria);
    }

    @Operation(summary = "modificar la informacion de auditoria por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Auditoria> editar(@PathVariable Integer id,@RequestBody Auditoria auditoria) {
        Optional<Auditoria> existe = auditoriaService.getAuditoria(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build(); //404
        }
        auditoria.setIdTrazabilidad(id);
        Auditoria actualizada = auditoriaService.saveAuditoria(auditoria);
        return ResponseEntity.ok(actualizada); //200
    }
    @Operation(summary = "eliminar auditoria por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            auditoriaService.deleteAuditoria(id);
            return ResponseEntity.noContent().build(); //404
        }catch (Exception e){
            return ResponseEntity.notFound().build(); //204
        }
    }

    @Operation(summary = "Obtiene la información de auditoria junto a su comuna por ID")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<AuditoriaDetalleDto>> getDetalleAlerta(
            @PathVariable Integer id) {

        AuditoriaDetalleDto detalle = auditoriaService.getAuditoriaConComuna(id);

        EntityModel<AuditoriaDetalleDto> model = EntityModel.of(detalle);
        //version automatica
        model.add(
                linkTo(methodOn(AuditoriaController.class).getDetalleAlerta(id))
                        .withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias",
                        "todas-las-auditorias")
        );
        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias/{id}",
                        "buscar-auditoria-por-su-id")
        );

        return ResponseEntity.ok(model);
    }
}