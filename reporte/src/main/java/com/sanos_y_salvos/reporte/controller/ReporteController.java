package com.sanos_y_salvos.reporte.controller;

import com.sanos_y_salvos.reporte.dto.ReporteDetalleDto;
import com.sanos_y_salvos.reporte.model.Reporte;
import com.sanos_y_salvos.reporte.service.ReporteService;
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
@Tag(name = "reporte", description = "operaciones relacionadas con los reportes")
@RequestMapping("/api/v1/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;


    @Operation(summary = "obtiene la informacion de todos los reportes")
    @GetMapping
    public CollectionModel<Reporte> listar() {
        List<Reporte> reportes = reporteService.getReportes();

        CollectionModel<Reporte> model = CollectionModel.of(reportes);
        //version automatica
        model.add(
                linkTo(
                        methodOn(ReporteController.class)
                                .listar()
                ).withSelfRel()
        );
        //version manual
        model.add(
                Link.of("http://localhost:8087/api/v1/reportes/{id}", "buscar-reportes-por-su-id")
        );

        model.add(
                Link.of("http://localhost:8087/api/v1/reportes/detalles", "reportes-con-detalles")
        );

        return model;
    }
    //public ResponseEntity<List<Reporte>> listar (){
      //  List<Reporte> reportes = reporteService.getReportes();
        //if (reportes.isEmpty()){
          //  return ResponseEntity.noContent().build();//204 no content
       // }else{
         //   return ResponseEntity.ok(reportes);//201 ok
       // }
    //}

    @Operation(summary = "obtiene la informacion de un reporte por su id")
    @GetMapping("/{id}")
    public EntityModel<Reporte> getReportes(@PathVariable Integer id) {
        Reporte reporte = reporteService.getReporte(id).orElseThrow();
        EntityModel<Reporte> model = EntityModel.of(reporte);
        model.add(
                linkTo(methodOn(ReporteController.class).getReportes(id)
                ).withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8087/api/v1/reportes", "todos-los-reportes")
        );

        model.add(
                Link.of("http://localhost:8087/api/v1/reportes/detalles", "reportes-con-detalles")
        );
        return model;
    }
    //public ResponseEntity<Reporte> buscarPorId(@PathVariable Integer id){
      //  Optional<Reporte> reporte = reporteService.getReporte(id);
//        if (reporte.isPresent())

  //          return ResponseEntity.ok(reporte.get());   // 201 - OK
    //    else
      //      return ResponseEntity.notFound().build();   // 404 - Not Found
    //}

    @Operation(summary = "agregar un nuevo reporte")
    @PostMapping
    public ResponseEntity<Reporte> agregarReporte(@RequestBody Reporte reporte){
        Reporte reporteNuevo = reporteService.saveReporte(reporte);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 - Created
                .body(reporteNuevo);
    }

    @Operation(summary = "modificar un reporte por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Reporte> editar(@PathVariable Integer id,@RequestBody Reporte reporte) {
        Optional<Reporte> existe = reporteService.getReporte(id);

        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        reporte.setIdReporte(id);

        Reporte actualizada = reporteService.saveReporte(reporte);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "eliminar un reporte por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            reporteService.deleteReporte(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "mostrar informacion de reportes junto a su comuna")
    @GetMapping("/detalles")
    public EntityModel<ReporteDetalleDto> getReporteDetalleDto(@PathVariable Integer id) {
        ReporteDetalleDto detalle = reporteService.getReporteDetalle(id);
        EntityModel<ReporteDetalleDto> model = EntityModel.of(detalle);
        model.add(
                linkTo(methodOn(ReporteController.class).getReporteDetalleDto(id))
                        .withSelfRel()
        );

        model.add(
                Link.of("http://localhost:8087/api/v1/reportes", "todos-los-reportes")
        );

        model.add(
                Link.of("http://localhost:8087/api/v1/reportes/{id}", "buscar-reportes-por-su-id")
        );

        return model;
    }
    //public ReporteDetalleDto getReporteDetalleDto(){return reporteService.getReporteDetalle();}

}