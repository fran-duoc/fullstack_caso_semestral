package com.sanos_y_salvos.institucion;

import com.sanos_y_salvos.institucion.model.TipoInstitucion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class gestionTipoTest {
    @Test
    void RegistrarTipoInstitucion1() {
        // ARRANGE (preparar)
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        Faker faker = new Faker();

        String descripcionFaker = faker.educator().course();

        tipoInstitucion.setIdTipo(1);
        tipoInstitucion.setDescripcion(descripcionFaker);

        // ACT (Ejecutar)
        System.out.println(tipoInstitucion);

        // ASSERT
        assertNotNull(tipoInstitucion);
        assertEquals(descripcionFaker, tipoInstitucion.getDescripcion()); // Pasa con éxito
        assertTrue(tipoInstitucion.getIdTipo() == 1);
        assertNotNull(tipoInstitucion.getIdTipo());
    }

    /*@Test
    void RegistrarTipoInstitucion2() {
        // ARRANGE (preparar)
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        Faker faker = new Faker();

        tipoInstitucion.setIdTipo(2);
        tipoInstitucion.setDescripcion(faker.educator().course());

        // ACT (Ejecutar)
        System.out.println(tipoInstitucion);

        // ASSERT
        assertNotNull(tipoInstitucion);
        assertEquals("Tipo Institucion Generica", tipoInstitucion.getDescripcion());
        assertFalse(tipoInstitucion.getIdTipo() == null);
    }*/
}
