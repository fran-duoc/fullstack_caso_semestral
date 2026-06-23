package com.sanos_y_salvos.mascotas;

import com.sanos_y_salvos.mascotas.model.Mascota;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionMascotasTest {
    /*@Test
    void RegistrarMascota1() {
        //ARRANGE (preparar)
        Mascota mascota = new Mascota();
        Faker faker = new Faker();

        //mascota.setNombreMascota(faker.educator().course());
        mascota.setNombreMascota("Mascota generica 1");
        mascota.setDiagnostico(faker.lorem().sentence());

        //ACT (Ejecutar)
        System.out.println(mascota);

        //ASSERT
        assertNotNull(mascota);
        assertEquals("Mascota generica 2", mascota.getNombreMascota());
    }*/

    @Test
    void RegistrarMascota2() {
        //ARRANGE (preparar)
        Mascota mascota = new Mascota();
        Faker faker = new Faker();

        mascota.setNombreMascota("Mascota generica 2");
        mascota.setDiagnostico(faker.lorem().sentence());

        //ACT (Ejecutar)
        System.out.println(mascota);

        //ASSERT
        assertNotNull(mascota);
        assertEquals("Mascota generica 2", mascota.getNombreMascota());
    }

    /*@Test
    void RegistrarMascota3() {
        //ARRANGE (preparar)
        Mascota mascota = new Mascota();
        Faker faker = new Faker();

        mascota.setNombreMascota("Mascota generica 2");
        mascota.setDiagnostico(null);
        mascota.setColor("blanco con manchas negras");

        //ACT (Ejecutar)
        System.out.println(mascota);

        //ASSERT
        assertNotNull(mascota);
        assertEquals("Mascota generica 2", mascota.getNombreMascota());
        assertNotNull(mascota.getDiagnostico());
        assertEquals("blanco con manchas negras", mascota.getColor());
    }*/

    /*@Test
    void RegistrarMascota4() {
        //ARRANGE (preparar)
        Mascota mascota = new Mascota();
        Faker faker = new Faker();

        mascota.setNombreMascota("Mascota generica 2");
        mascota.setDiagnostico(faker.lorem().sentence());
        mascota.setColor("naranja");

        //ACT (Ejecutar)
        System.out.println(mascota);

        //ASSERT
        assertNotNull(mascota);
        assertEquals("Mascota generica 2", mascota.getNombreMascota());
        assertNotNull(mascota.getDiagnostico());
        assertEquals("blanco con manchas negras", mascota.getColor());
    }*/

    /*@Test
    void RegistrarMascota5() {
        //ARRANGE (preparar)
        Mascota mascota = new Mascota();
        Faker faker = new Faker();

        mascota.setNombreMascota("Mascota generica 2");
        mascota.setDiagnostico(faker.lorem().sentence());
        mascota.setColor("blanco con manchas negras");
        mascota.setEdad(2);

        //ACT (Ejecutar)
        System.out.println(mascota);

        //ASSERT
        assertNotNull(mascota);
        assertEquals("Mascota generica 2", mascota.getNombreMascota());
        assertNotNull(mascota.getDiagnostico());
        assertEquals("blanco con manchas negras", mascota.getColor());
        assertFalse(mascota.getEdad()!= null);
    }*/
}

