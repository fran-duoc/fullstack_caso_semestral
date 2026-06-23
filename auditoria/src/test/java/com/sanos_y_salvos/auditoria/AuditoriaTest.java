package com.sanos_y_salvos.auditoria;

import com.sanos_y_salvos.auditoria.model.Auditoria;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AuditoriaTest {

        @Test
        void RegistrarAuditoria1() {
            Auditoria auditoria = new Auditoria();
            Faker faker = new Faker();

            auditoria.setPNombre("Auditoria generica 1");
            auditoria.setAccion(faker.lorem().sentence());
            auditoria.setIdComuna(1);
            auditoria.setResultado("EXITOSO");

            System.out.println(auditoria);

            assertNotNull(auditoria);
            assertEquals("Auditoria generica 1", auditoria.getPNombre());
        }

        @Test
        void RegistrarAuditoria2() {
            Auditoria auditoria = new Auditoria();
            Faker faker = new Faker();

            auditoria.setPNombre("Auditoria generica 2");
            auditoria.setAccion(faker.lorem().sentence());
            auditoria.setIdComuna(1);
            auditoria.setResultado("EXITOSO");

            System.out.println(auditoria);
            assertNotNull(auditoria);
            assertEquals("Auditoria generica 2", auditoria.getPNombre());
        }

        /*@Test
        void RegistrarAuditoria3() {
            Auditoria auditoria = new Auditoria();
            Faker faker = new Faker();

            auditoria.setPNombre("Auditoria generica 2");
            auditoria.setAccion(null);
            auditoria.setIdComuna(1);
            auditoria.setResultado("FALLIDO");

            System.out.println(auditoria);

            assertNotNull(auditoria);
            assertEquals("Auditoria generica 2", auditoria.getPNombre());
            assertNotNull(auditoria.getAccion());
            assertEquals("FALLIDO", auditoria.getResultado());
        }*/

        /*@Test
        void RegistrarAuditoria4() {
            Auditoria auditoria = new Auditoria();
            Faker faker = new Faker();

            auditoria.setPNombre("Auditoria generica 2");
            auditoria.setAccion(faker.lorem().sentence());
            auditoria.setResultado("EXITOSO");
            auditoria.setFechaAcceso(LocalDateTime.now());

             System.out.println(auditoria);

            assertNotNull(auditoria);
            assertEquals("Auditoria generica 2", auditoria.getPNombre());
            assertNotNull(auditoria.getAccion());
            assertEquals("EXITOSO", auditoria.getResultado());
            assertFalse(auditoria.getFechaAcceso() != null);
        }*/

}
