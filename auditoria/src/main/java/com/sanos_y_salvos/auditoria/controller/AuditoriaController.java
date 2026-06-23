package com.sanos_y_salvos.auditoria.controller;

import com.sanos_y_salvos.auditoria.dto.AuditoriaDetalleDto;
import com.sanos_y_salvos.auditoria.model.Auditoria;
import com.sanos_y_salvos.auditoria.service.AuditoriaService;
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
                linkTo(
                        methodOn(AuditoriaController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8079/api/v1/auditorias/{id}", "buscar-auditoria-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8079/api/v1/auditorias/{id}/detalle", "buscar-auditoria-con-detalle")
        );

        return model;
    }
    //public ResponseEntity<List<Auditoria>> listar (){
     //   List<Auditoria> auditorias = auditoriaService.obtenerTodosLosAccesos();
      //  if (auditorias.isEmpty()){
        //    return ResponseEntity.noContent().build();//204 no content
        //}else{
          //  return ResponseEntity.ok(auditorias);//201 ok
        //}
    //}

    @Operation(summary = "Obtiene la información de una auditoría por su id")
    @GetMapping("/{id}")
    public EntityModel<Auditoria> getAuditoria(@PathVariable Integer id){
        Auditoria auditoria = auditoriaService.getAuditoria(id).orElseThrow();
        EntityModel<Auditoria> model = EntityModel.of(auditoria);
        model.add(
                linkTo( methodOn(AuditoriaController.class).getAuditoria(id)
                ).withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8079/api/v1/auditorias", "todas-las-auditorias")
        );
        model.add(
                Link.of("http://localhost:8079/api/v1/auditorias/{id}/detalle", "buscar-auditoria-con-detalle")
        );
        return model;
    }

    //public ResponseEntity<Auditoria> buscarPorId(@PathVariable Integer id){
      //  Optional<Auditoria> auditoria = Optional.empty();
       // if (auditoria.isPresent())
         //   return ResponseEntity.ok(auditoria.get());   // 201 - OK
        //else
          //  return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar nueva auditoria")
    @PostMapping
    public ResponseEntity<Auditoria> agregarInstitucion(@RequestBody Auditoria auditoria){
        Auditoria auditoriaNueva = auditoriaService.saveAuditoria(auditoria);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(auditoriaNueva);
    }

    @Operation(summary = "modifica una auditoria por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Auditoria> editar(@PathVariable Integer id,@RequestBody Auditoria auditoria) {
        Optional<Auditoria> existe = Optional.empty();

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        auditoria.setIdTrazabilidad(id);

        Auditoria actualizada = auditoriaService.updateAuditoria(auditoria);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "elimina una auditoria por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Obtiene la información de una auditoría junto a su comuna por ID")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<AuditoriaDetalleDto>> getDetalleAuditoria(@PathVariable Integer id) {
        AuditoriaDetalleDto detalle = auditoriaService.getAuditoriaConComuna(id);

        EntityModel<AuditoriaDetalleDto> model = EntityModel.of(detalle);

        model.add(
                linkTo(methodOn(AuditoriaController.class).getDetalleAuditoria(id))
                        .withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias/{id}",
                        "buscar-auditoria-por-su-id")
        );

        model.add(
                Link.of("http://localhost:8089/api/v1/auditorias",
                        "todas-las-auditorias")
        );

        return ResponseEntity.ok(model);
    }
   // @Operation(summary = "Obtiene la información de auditorías junto a su comuna")
    //@GetMapping("/detalle")
    //public AuditoriaDetalleDto auditoriaDetalleDto() {
      //  return auditoriaService.getAuditoriaConComuna();
   // }
}