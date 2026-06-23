package com.sanos_y_salvos.mascotas;

import com.sanos_y_salvos.mascotas.model.Sexo;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionSexosTest {
    @Test
    void RegistrarSexo1() {
        // ARRANGE (preparar)
        Sexo sexo = new Sexo();
        Faker faker = new Faker();

        String opcionSexo = faker.options().option("Macho", "Hembra");

        sexo.setIdSexo(1);
        sexo.setDescripcion(opcionSexo);

        // ACT (Ejecutar)
        System.out.println(sexo);

        // ASSERT
        assertNotNull(sexo);
        assertEquals(opcionSexo, sexo.getDescripcion());
        assertEquals(1, sexo.getIdSexo());
        assertTrue(sexo.getDescripcion().length() > 0);
    }

    @Test
    void RegistrarSexo2() {
        // ARRANGE (preparar)
        Sexo sexo = new Sexo();
        Faker faker = new Faker();

        String descripcionAleatoria = faker.lorem().word();

        sexo.setDescripcion(descripcionAleatoria);

        // ACT (Ejecutar)
        System.out.println(sexo);

        // ASSERT
        assertNotNull(sexo);
        assertEquals(descripcionAleatoria, sexo.getDescripcion());
        assertNull(sexo.getIdSexo());
    }

    /*@Test
    void RegistrarSexo3() {
        // ARRANGE (preparar)
        Sexo sexo = new Sexo();
        Faker faker = new Faker();

        String descripcionFaker = faker.options().option("Macho", "Hembra");

        sexo.setIdSexo(2);
        sexo.setDescripcion(descripcionFaker);

        // ACT (Ejecutar)
        System.out.println(sexo);

        // ASSERT
        assertNotNull(sexo);
        assertEquals(descripcionFaker, sexo.getDescripcion());

        assertTrue(sexo.getIdSexo() == 2);

        assertFalse(sexo.getIdSexo() != null);
    }*/
}
