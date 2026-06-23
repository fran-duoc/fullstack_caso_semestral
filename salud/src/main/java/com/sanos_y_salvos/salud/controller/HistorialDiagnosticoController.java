package com.sanos_y_salvos.salud.controller;

import com.sanos_y_salvos.salud.dto.FuncionarioDto;
import com.sanos_y_salvos.salud.dto.SaludDetalleDto;
import com.sanos_y_salvos.salud.model.HistorialDiagnostico;
import com.sanos_y_salvos.salud.service.HistorialDiagnosticoService;
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
@Tag(name = "historial", description = "operaciones relacionadas con los historiales de salud")
@RequestMapping("/api/v1/historial")
public class HistorialDiagnosticoController {
    @Autowired
    private HistorialDiagnosticoService historialDiagnosticoService;

    @Operation(summary = "obtiene la informacion de todos los historiales de diagnostico")
    @GetMapping
    public CollectionModel<HistorialDiagnostico> listar() {
        List<HistorialDiagnostico> historialDiagnosticos = historialDiagnosticoService.getHistorialDiagnosticos();

        CollectionModel<HistorialDiagnostico> model = CollectionModel.of(historialDiagnosticos);
        //version automatica
        model.add(
                linkTo(
                        methodOn(HistorialDiagnosticoController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8091/api/v1/historial/{id}", "buscar-historial-por-su-id")
        );
        model.add(
                Link.of("http://localhost:8091/api/v1/historial/detalle", "todos-los-historialSalud-con-detalles")
        );
        return model;
    }

    //if(historialDiagnosticos.isEmpty()){
    //  return ResponseEntity.noContent().build();
    //}else {
    //   return ResponseEntity.ok(historialDiagnosticos);
    //}

    @Operation(summary = "obtiene la informacion de un historial de diagnostico por su id")
    @GetMapping("/{id}")
    public EntityModel<HistorialDiagnostico> getHistorial
            (@PathVariable Integer id) {
        HistorialDiagnostico historialDiagnostico = historialDiagnosticoService.getHistorialDiagnostico(id).orElseThrow();
        EntityModel<HistorialDiagnostico> model = EntityModel.of(historialDiagnostico);
        //version automatica
        model.add(
                linkTo(
                        methodOn(HistorialDiagnosticoController.class)
                                .getHistorial(id)
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of(
                        "http://localhost:8091/api/v1/historial",
                        "todas-los-historiales"
                )
        );
        model.add(
                Link.of("http://localhost:8091/api/v1/historial/detalles", "todos-los-historialSalud-con-detalles")
        );
        return model;
    }


    /*public ResponseEntity<HistorialDiagnostico> buscarPorId(@PathVariable Integer id){
        Optional<HistorialDiagnostico> historialDiagnostico = historialDiagnosticoService.getHistorialDiagnostico(id);
        if (historialDiagnostico.isPresent()){
            return ResponseEntity.ok(historialDiagnostico.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }*/

    @Operation(summary = "agregar un nuevo historiales de diagnostico")
    @PostMapping
    public ResponseEntity<HistorialDiagnostico> agregarHistorialDiagnostico(@RequestBody HistorialDiagnostico historialDiagnostico) {
        HistorialDiagnostico historialDiagnosticoNuevo = historialDiagnosticoService.saveHistorial(historialDiagnostico);

        return ResponseEntity.status(HttpStatus.CREATED).body(historialDiagnosticoNuevo);
    }

    @Operation(summary = "modificar un historial de diagnostico por su id")
    @PutMapping("/{id}")
    public ResponseEntity<HistorialDiagnostico> editar(@PathVariable Integer id, @RequestBody HistorialDiagnostico historialDiagnostico) {
        Optional<HistorialDiagnostico> existe = historialDiagnosticoService.getHistorialDiagnostico(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        historialDiagnostico.setId(id);

        HistorialDiagnostico actualizada = historialDiagnosticoService.saveHistorial(historialDiagnostico);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "eliminar un historial de diagnostico por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            historialDiagnosticoService.deleteHistorialDiagnostico(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "obtiene la informacion de todos los historiales de diagnostico junto a su funcionario y mascota")
    @GetMapping("/detalle")
    public ResponseEntity<?> listarDetalles(@PathVariable Integer id) {
        try {
            // Intentamos obtener el detalle del servicio
            SaludDetalleDto detalle = historialDiagnosticoService.getSaludconFuncionarioMascota(id);

            if (detalle == null) {
                return ResponseEntity.noContent().build();
            }

            EntityModel<SaludDetalleDto> model = EntityModel.of(detalle);

            // Versión automática
            model.add(
                    linkTo(methodOn(HistorialDiagnosticoController.class).listarDetalles(id)).withSelfRel()
            );
            // Versión manual
            model.add(
                    Link.of("http://localhost:8091/api/v1/historial/{id}", "buscar-historial-por-su-id")
            );
            model.add(
                    Link.of("http://localhost:8091/api/v1/historial", "todas-los-historiales")
            );

            return ResponseEntity.ok(model);

        } catch (Exception e) { //atrapa el error 500
            return ResponseEntity.status(500).body("Error en el servicio: " + e.getMessage()
                    + ". Asegúrate de que exista Funcionario y Mascota en los otros microservicios.");
        }
        //public SaludDetalleDto getSaludDetalleDto(){return historialDiagnosticoService.getSaludconFuncionarioMascota();}

    }
}




