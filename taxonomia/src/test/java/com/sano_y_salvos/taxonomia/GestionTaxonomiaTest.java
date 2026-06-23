package com.sano_y_salvos.taxonomia;

import com.sano_y_salvos.taxonomia.model.Especie;
import com.sano_y_salvos.taxonomia.model.Raza;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionTaxonomiaTest {
    @Test
    void RegistrarEspecie1() {
        // ARRANGE (preparar) - TEST BUENO DE ESPECIE
        Especie especie = new Especie();
        Faker faker = new Faker();

        String nombreEspecieFaker = faker.animal().name();

        especie.setIdEspecie(1);
        especie.setNombreEspecie(nombreEspecieFaker);

        // ACT (Ejecutar)
        System.out.println(especie);

        // ASSERT
        assertNotNull(especie);
        assertEquals(nombreEspecieFaker, especie.getNombreEspecie());
        assertTrue(especie.getIdEspecie() == 1);
    }

    @Test
    void RegistrarRaza2() {
        // ARRANGE (preparar)
        Especie especie = new Especie();
        Raza raza = new Raza();
        Faker faker = new Faker();

        String nombreEspecieFaker = faker.animal().name();
        String nombreRazaFaker = faker.animal().name();

        especie.setIdEspecie(2);
        especie.setNombreEspecie(nombreEspecieFaker);

        raza.setIdRaza(10);
        raza.setNombreRaza(nombreRazaFaker);
        raza.setEspecie(especie);

        // ACT (Ejecutar)
        System.out.println(raza);

        // ASSERT
        assertNotNull(raza);
        assertEquals(nombreRazaFaker, raza.getNombreRaza());
        assertNotNull(raza.getEspecie());
        assertEquals(nombreEspecieFaker, raza.getEspecie().getNombreEspecie());
    }

    /*@Test
    void RegistrarEspecie3() {
        // ARRANGE (preparar)
        Especie especie = new Especie();
        Faker faker = new Faker();

        especie.setIdEspecie(3);
        especie.setNombreEspecie(faker.animal().name());

        // ACT (Ejecutar)
        System.out.println(especie);

        // ASSERT
        assertNotNull(especie);
        assertEquals("Especie Generica", especie.getNombreEspecie());
    }*/

    /*@Test
    void RegistrarRaza4() {
        // ARRANGE (preparar)
        Raza raza = new Raza();
        Especie especie = new Especie();
        Faker faker = new Faker();

        especie.setNombreEspecie(faker.animal().name());

        raza.setIdRaza(4);
        raza.setNombreRaza(faker.animal().name());
        raza.setEspecie(especie);

        // ACT (Ejecutar)
        System.out.println(raza);

        // ASSERT
        assertNotNull(raza);
        assertNotNull(raza.getEspecie());
        assertTrue(raza.getIdRaza() == 4);
        assertFalse(raza.getIdRaza() != null);
    }*/
}
