package com.sanos_y_salvos.alerta;

import com.sanos_y_salvos.alerta.model.Alerta;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlertasTest {
    @Test
    void RegistrarAlerta(){
        //arrange(preparar)
        Alerta alerta = new Alerta();
        Faker faker = new Faker();

        alerta.setMedioNotificacion("Telefono");


        //act
        System.out.println(alerta);
        //assert
        assertNotNull(alerta);
        assertEquals("Telefono", alerta.getMedioNotificacion());

    }
    @Test
    void RegistrarAlerta2(){
        //arrange(preparar)
        Alerta alerta = new Alerta();
        Faker faker = new Faker();

        alerta.setMedioNotificacion("sms");


        //act
        System.out.println(alerta);
        //assert
        assertNotNull(alerta);
        assertEquals("sms", alerta.getMedioNotificacion());

    }
    @Test
    void RegistrarAlerta3(){
        //arrange(preparar)
        Alerta alerta = new Alerta();
        Faker faker = new Faker();

        alerta.setMedioNotificacion("correo");


        //act
        System.out.println(alerta);
        //assert
        assertNotNull(alerta);
        assertEquals("correo", alerta.getMedioNotificacion());

    }
}
