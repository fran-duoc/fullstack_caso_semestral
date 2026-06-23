package com.sanos_y_salvos.personal;

import com.sanos_y_salvos.personal.model.Cargo;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CargoTest {
    /*@Test
    void RegistrarCargo(){
        Cargo cargo = new Cargo();
        Faker faker = new Faker();

        cargo.setIdCargo(1);
        cargo.setNombreCargo("Secretaria");
        cargo.setDescripcion("Cuidado y Salud de animales rescatados");

        System.out.println(cargo);

        assertNotNull(cargo);
        assertEquals("Veterinario", cargo.getNombreCargo());
        assertEquals("Cuidado y Salud de animales rescatados",cargo.getDescripcion());

    }*/
    @Test
    void RegistrarCargo1(){
        Cargo cargo = new Cargo();
        Faker faker = new Faker();

        cargo.setIdCargo(2);
        cargo.setNombreCargo("Secretaria");
        cargo.setDescripcion("Recepción de animales rescatados");

        System.out.println(cargo);

        assertNotNull(cargo);
        assertEquals("Secretaria", cargo.getNombreCargo());
        assertEquals("Recepción de animales rescatados",cargo.getDescripcion());

    }
    /*@Test
    void RegistrarCargo2(){
        Cargo cargo = new Cargo();
        Faker faker = new Faker();

        cargo.setIdCargo(3);
        cargo.setNombreCargo("Voluntario");
        cargo.setDescripcion("Misiones designadas");

        System.out.println(cargo);

        assertNotNull(cargo);
        assertEquals("Voluntario", cargo.getNombreCargo());
        assertEquals("Recepción de animales rescatados",cargo.getDescripcion());

    }*/
}
