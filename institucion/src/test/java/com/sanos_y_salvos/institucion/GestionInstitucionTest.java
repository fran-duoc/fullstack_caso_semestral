package com.sanos_y_salvos.institucion;

import com.sanos_y_salvos.institucion.model.Institucion;
import com.sanos_y_salvos.institucion.model.TipoInstitucion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionInstitucionTest {
    @Test
    void RegistrarInstitucion1() {
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
        assertEquals(descripcionFaker, tipoInstitucion.getDescripcion()); // Pasa exitosamente
        assertTrue(tipoInstitucion.getIdTipo() == 1);
    }

    @Test
    void RegistrarTipoInstitucion2() {
        // ARRANGE (preparar)
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        Faker faker = new Faker();

        String descripcionFaker = faker.company().name();

        tipoInstitucion.setIdTipo(2);
        tipoInstitucion.setDescripcion(descripcionFaker);

        // ACT (Ejecutar)
        System.out.println(tipoInstitucion);

        // ASSERT
        assertNotNull(tipoInstitucion);
        assertEquals(descripcionFaker, tipoInstitucion.getDescripcion()); // Pasa exitosamente
        assertNotNull(tipoInstitucion.getIdTipo());
    }

    /*@Test
    void RegistrarInstitucion3() {
        // ARRANGE (preparar)
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        Faker faker = new Faker();

        tipoInstitucion.setIdTipo(3);
        tipoInstitucion.setDescripcion(faker.educator().course()); // Genera texto aleatorio dinámico

        // ACT (Ejecutar)
        System.out.println(tipoInstitucion);

        // ASSERT
        assertNotNull(tipoInstitucion);
        assertEquals("Institucion generica 3", tipoInstitucion.getDescripcion());
    }*/

    /*@Test
    void RegistrarInstitucion4() {
        // ARRANGE (preparar)
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        Faker faker = new Faker();

        tipoInstitucion.setIdTipo(4);
        tipoInstitucion.setDescripcion(faker.educator().course());

        // ACT (Ejecutar)
        System.out.println(tipoInstitucion);

        // ASSERT
        assertNotNull(tipoInstitucion);
        assertTrue(tipoInstitucion.getIdTipo() == 4);

        assertFalse(tipoInstitucion.getIdTipo() != null);
    }*/
    }
