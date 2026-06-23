package com.sanos_y_salvos.localizacion;

import com.sanos_y_salvos.localizacion.model.Comuna;
import com.sanos_y_salvos.localizacion.model.Provincia;
import com.sanos_y_salvos.localizacion.model.Region;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionLocalizacionTest {
  /*  @Test
    void RegistrarLocalizacion1() {
        // ARRANGE (preparar)
        Region region = new Region();
        Faker faker = new Faker();

        region.setNombreRegion("Región Metropolitana");

        // ACT
        System.out.println(region);

        // ASSERT
        assertNotNull(region);
        assertEquals("Región de Valparaíso", region.getNombreRegion());
    }*/

    @Test
    void RegistrarLocalizacion2() {
        // ARRANGE
        Provincia provincia = new Provincia();
        Faker faker = new Faker();

        provincia.setNombreProvincia("Provincia de Santiago");

        // ACT
        System.out.println(provincia);

        // ASSERT
        assertNotNull(provincia);
        assertEquals("Provincia de Santiago", provincia.getNombreProvincia());
    }

    /*@Test
    void RegistrarLocalizacion3() {
        // ARRANGE (preparar)
        Comuna comuna = new Comuna();
        Faker faker = new Faker();

        comuna.setNombreColumna("Puente Alto");
        comuna.setProvincia(null);

        // ACT
        System.out.println(comuna);

        // ASSERT
        assertNotNull(comuna);
        assertEquals("Puente Alto", comuna.getNombreColumna());
        assertTrue(comuna.getNombreColumna().startsWith("Puente"));
        assertNotNull(comuna.getProvincia());
    }*/

    /*@Test
    void RegistrarLocalizacion4() {
        // ARRANGE
        Provincia provincia = new Provincia();
        Region region = new Region();
        Faker faker = new Faker();

        region.setNombreRegion("Región de Coquimbo");

        provincia.setNombreProvincia("Provincia de Elqui");
        provincia.setRegion(region);

        // ACT
        System.out.println(provincia);

        // ASSERT
        assertNotNull(provincia);
        assertEquals("Provincia de Elqui", provincia.getNombreProvincia());
        assertNotNull(provincia.getRegion());
        assertEquals("Región del Biobío", provincia.getRegion().getNombreRegion());
    }*/

    /*@Test
    void RegistrarLocalizacion5() {
        // ARRANGE (preparar)
        Comuna comuna = new Comuna();
        Provincia provincia = new Provincia();
        Faker faker = new Faker();

        provincia.setNombreProvincia(faker.address().state());

        comuna.setIdComuna(10);
        comuna.setNombreColumna("La Florida");
        comuna.setProvincia(provincia);

        // ACT
        System.out.println(comuna);

        // ASSERT
        assertNotNull(comuna);
        assertEquals("La Florida", comuna.getNombreColumna());
        assertNotNull(comuna.getProvincia());
        assertTrue(comuna.getIdComuna() > 0);
        assertFalse(comuna.getIdComuna() != null);
    }*/
}
