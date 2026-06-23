package com.sanos_y_salvos.reporte;

import com.sanos_y_salvos.reporte.model.Reporte;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteTest {
    /*@Test
    void RegistrarReporte1() {
        //ARRANGE (preparar)
        Reporte reporte = new Reporte();
        Faker faker = new Faker();

        reporte.setTipoReporte("Reporte generico 1");
        reporte.setDescripcion(faker.lorem().sentence());
        reporte.setIdFuncionario(1);
        reporte.setEstadoReporte("PENDIENTE");

        //ACT (Ejecutar)
        System.out.println(reporte);

        //ASSERT
        assertNotNull(reporte);
        assertEquals("Reporte generico 2", reporte.getTipoReporte());
    }*/

    @Test
    void RegistrarReporte2() {
        //ARRANGE (preparar)
        Reporte reporte = new Reporte();
        Faker faker = new Faker();

        reporte.setTipoReporte("Reporte generico 2");
        reporte.setDescripcion(faker.lorem().sentence());
        reporte.setIdFuncionario(1);
        reporte.setEstadoReporte("PENDIENTE");

        //ACT (Ejecutar)
        System.out.println(reporte);

        //ASSERT
        assertNotNull(reporte);
        assertEquals("Reporte generico 2", reporte.getTipoReporte());
    }

    /*@Test
    void RegistrarReporte3() {
        //ARRANGE (preparar)
        Reporte reporte = new Reporte();
        Faker faker = new Faker();

        reporte.setTipoReporte("Reporte generico 2");
        reporte.setDescripcion(null);
        reporte.setIdFuncionario(1);
        reporte.setEstadoReporte("COMPLETADO");

        //ACT (Ejecutar)
        System.out.println(reporte);

        //ASSERT
        assertNotNull(reporte);
        assertEquals("Reporte generico 2", reporte.getTipoReporte());
        assertNotNull(reporte.getDescripcion());
        assertEquals("COMPLETADO", reporte.getEstadoReporte());
    }*/

    /*@Test
    void RegistrarReporte4() {
        //ARRANGE (preparar)
        Reporte reporte = new Reporte();
        Faker faker = new Faker();

        reporte.setTipoReporte("Reporte generico 2");
        reporte.setDescripcion(faker.lorem().sentence());
        reporte.setIdFuncionario(1);
        reporte.setEstadoReporte("PENDIENTE");
        reporte.setFechaSuceso(LocalDate.now());

        //ACT (Ejecutar)
        System.out.println(reporte);

        //ASSERT
        assertNotNull(reporte);
        assertEquals("Reporte generico 2", reporte.getTipoReporte());
        assertNotNull(reporte.getDescripcion());
        assertEquals("PENDIENTE", reporte.getEstadoReporte());
        assertFalse(reporte.getFechaSuceso() != null);
    }*/
}
