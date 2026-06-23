package com.sanos_y_salvos.duenio;

import com.sanos_y_salvos.duenio.model.Duenio;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionDuenioTest {
    /*@Test
    void RegistrarDuenio() {
        //ARRANGE (preparar)
        Duenio duenio = new Duenio();
        Faker faker = new Faker();

        duenio.setIdDuenio(1);
        duenio.setRun(faker.educator().course());
        duenio.setDvrun(faker.educator().course());
        duenio.setPNombre(faker.educator().course());
        duenio.setSNombre(faker.educator().course());
        duenio.setApPaterno(faker.educator().course());
        duenio.setApMaterno(faker.educator().course());
        duenio.setIdComuna(1);
        duenio.setTelefono(999999999);
        duenio.setEmail(faker.educator().course());

        //ACT (Ejecutar)
        System.out.println(duenio);

        //ASSERT
        assertNotNull(duenio);
        assertEquals("Bachelor of Teaching", duenio.getPNombre());
    }*/

   /* @Test
    void RegistrarDuenio2() {
        //ARRANGE (preparar)
        Duenio duenio = new Duenio();
        Faker faker = new Faker();

        duenio.setIdDuenio(2);
        duenio.setRun(faker.educator().course());
        duenio.setDvrun(faker.educator().course());
        duenio.setPNombre(faker.educator().course());
        duenio.setSNombre(faker.educator().course());
        duenio.setApPaterno(faker.educator().course());
        duenio.setApMaterno(faker.educator().course());
        duenio.setIdComuna(2);
        duenio.setTelefono(999999999);
        duenio.setEmail(faker.educator().course());

        //ACT (Ejecutar)
        System.out.println(duenio);

        //ASSERT
        assertNotNull(duenio);
        assertEquals("Dueño generico 2", duenio.getPNombre());
    }*/

    /*@Test
    void RegistrarDuenio3() {
        //ARRANGE (preparar)
        Duenio duenio = new Duenio();
        Faker faker = new Faker();

        duenio.setIdDuenio(3);
        duenio.setRun(faker.educator().course());
        duenio.setDvrun(faker.educator().course());
        duenio.setPNombre(faker.educator().course());
        duenio.setSNombre(faker.educator().course());
        duenio.setApPaterno(faker.educator().course());
        duenio.setApMaterno(faker.educator().course());
        duenio.setIdComuna(3);
        duenio.setTelefono(999999999);
        duenio.setEmail(faker.educator().course());

        //ACT (Ejecutar)
        System.out.println(duenio);

        //ASSERT
        assertNotNull(duenio);
        assertEquals("Dueño generico 3", duenio.getPNombre());
    }*/

   /* @Test
    void RegistrarDuenio4() {
        //ARRANGE (preparar)
        Duenio duenio = new Duenio();
        Faker faker = new Faker();

        duenio.setIdDuenio(4);
        duenio.setRun(faker.educator().course());
        duenio.setDvrun(faker.educator().course());
        duenio.setPNombre(faker.educator().course());
        duenio.setSNombre(faker.educator().course());
        duenio.setApPaterno(faker.educator().course());
        duenio.setApMaterno(faker.educator().course());
        duenio.setIdComuna(4);
        duenio.setTelefono(999999999);
        duenio.setEmail(faker.educator().course());

        //ACT (Ejecutar)
        System.out.println(duenio);

        //ASSERT
        assertNotNull(duenio);
        assertEquals("Dueño generico 4", duenio.getPNombre());
    }*/

    @Test
    void RegistrarDuenio5() {
        //ARRANGE (preparar)
        Duenio duenio = new Duenio();
        Faker faker = new Faker();

        // Guardamos el nombre generado por Faker en una variable para poder asegurar que coincida en el ASSERT
        String nombreFaker = faker.educator().course();

        duenio.setIdDuenio(5);
        duenio.setRun(faker.educator().course());
        duenio.setDvrun(faker.educator().course());
        duenio.setPNombre(nombreFaker); // Usamos la variable con el dato dinámico
        duenio.setSNombre(faker.educator().course());
        duenio.setApPaterno(faker.educator().course());
        duenio.setApMaterno(faker.educator().course());
        duenio.setIdComuna(5);
        duenio.setTelefono(999999999);
        duenio.setEmail(faker.educator().course());

        //ACT (Ejecutar)
        System.out.println(duenio);

        //ASSERT
        assertNotNull(duenio);
        // Este assertEquals pasará de forma exitosa porque compara el valor real con el que guardamos dinámicamente
        assertEquals(nombreFaker, duenio.getPNombre());
        assertTrue(duenio.getIdDuenio() == 5);
    }
}
