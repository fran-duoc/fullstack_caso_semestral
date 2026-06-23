package com.sanos_y_salvos.salud;

import com.sanos_y_salvos.salud.model.HistorialDiagnostico;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

public class GestionSaludTest {
    /*@Test
    void RegistrarSalud() {
        //ARRANGE (preparar)
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();

        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(1);
        historialDiagnostico.setRutFuncionario("");
        historialDiagnostico.setIdMascota(1);

        //ACT (Ejecutar)
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
        assertEquals("Historial Diagnostico", historialDiagnostico.getObservaciones());
    }*/

    /*@Test
    void RegistrarSalud2() {
        //ARRANGE (preparar)
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();

        //mascota.setNombreMascota(faker.educator().course());
        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(2);
        historialDiagnostico.setRutFuncionario("");
        historialDiagnostico.setIdMascota(2);

        //ACT (Ejecutar)
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
        assertEquals("Historial Diagnostico", historialDiagnostico.getObservaciones());
    }*/

    /*@Test
    void RegistrarSalud3() {
        //ARRANGE (preparar)
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();


        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(3);
        historialDiagnostico.setRutFuncionario("");
        historialDiagnostico.setIdMascota(3);

        //ACT (Ejecutar)
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
        assertEquals("Historial Diagnostico", historialDiagnostico.getObservaciones());
    }*/

    /*@Test
    void RegistrarSalud4() {
        //ARRANGE (preparar)
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();

        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(4);
        historialDiagnostico.setRutFuncionario("");
        historialDiagnostico.setIdMascota(4);

        //ACT (Ejecutar)
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
        assertEquals("Historial Diagnostico", historialDiagnostico.getObservaciones());
    }*/

    /*@Test
    void RegistrarSalud5() {
        //ARRANGE (preparar)
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();

        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(5);
        historialDiagnostico.setRutFuncionario("");
        historialDiagnostico.setIdMascota(5);

        //ACT (Ejecutar)
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
        assertEquals("Historial Diagnostico", historialDiagnostico.getObservaciones());
    }*/

    @Test
    void RegistrarSalid6(){
        //ARRANGE
        HistorialDiagnostico historialDiagnostico = new HistorialDiagnostico();
        Faker faker = new Faker();

        historialDiagnostico.setObservaciones(faker.educator().course());
        historialDiagnostico.setFechaAtencion(LocalDate.EPOCH);
        historialDiagnostico.setId(5);
        historialDiagnostico.setIdFuncionario(1);
        historialDiagnostico.setIdMascota(5);

        //ACT
        System.out.println(historialDiagnostico);

        //ASSERT
        assertNotNull(historialDiagnostico);
    }
}
