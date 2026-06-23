package com.sanos_y_salvos.personal;

import com.sanos_y_salvos.personal.model.Funcionario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {
    /*@Test
    void RegistrarFuncionario1() {
        // ARRANGE (preparar)
        Funcionario funcionario = new Funcionario();
        Faker faker = new Faker();

        funcionario.setIdFuncionario(1);
        funcionario.setRun(faker.number().numberBetween(10000000, 25000000));
        funcionario.setDvrun(faker.options().option("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "k"));
        funcionario.setPNombre(faker.name().firstName()); // Nombre dinámico y aleatorio
        funcionario.setSNombre(faker.name().firstName());
        funcionario.setApPaterno(faker.name().lastName());
        funcionario.setApMaterno(faker.name().lastName());
        funcionario.setEmail(faker.internet().emailAddress());

        // ACT (Ejecutar)
        System.out.println(funcionario);

        // ASSERT
        assertNotNull(funcionario);
        assertEquals(1, funcionario.getIdFuncionario());

        assertEquals("victor", funcionario.getPNombre());
    }*/

    /*@Test
    void RegistrarFuncionario2() {
        // ARRANGE (preparar)
        Funcionario funcionario = new Funcionario();
        Faker faker = new Faker();

        funcionario.setIdFuncionario(2);
        funcionario.setRun(faker.number().numberBetween(10000000, 25000000));
        funcionario.setDvrun(faker.options().option("k", "0", "1"));
        funcionario.setPNombre(faker.name().firstName());
        funcionario.setSNombre(faker.name().firstName());
        funcionario.setApPaterno(faker.name().lastName());
        funcionario.setApMaterno(faker.name().lastName());
        funcionario.setEmail(faker.internet().emailAddress());

        // ACT (Ejecutar)
        System.out.println(funcionario);

        // ASSERT
        assertNotNull(funcionario);
        assertTrue(funcionario.getIdFuncionario() == 2);
        assertFalse(funcionario.getIdFuncionario() != null);
    }*/

    @Test
    void RegistrarFuncionario3() {
        // ARRANGE (preparar)
        Funcionario funcionario = new Funcionario();
        Faker faker = new Faker();

        int runFaker = faker.number().numberBetween(10000000, 25000000);
        String dvFaker = faker.options().option("k", "4", "9");
        String pNombreFaker = faker.name().firstName();
        String sNombreFaker = faker.name().firstName();
        String apPaternoFaker = faker.name().lastName();
        String apMaternoFaker = faker.name().lastName();
        String emailFaker = faker.internet().emailAddress();

        funcionario.setIdFuncionario(3);
        funcionario.setRun(runFaker);
        funcionario.setDvrun(dvFaker);
        funcionario.setPNombre(pNombreFaker);
        funcionario.setSNombre(sNombreFaker);
        funcionario.setApPaterno(apPaternoFaker);
        funcionario.setApMaterno(apMaternoFaker);
        funcionario.setEmail(emailFaker);

        // ACT (Ejecutar)
        System.out.println(funcionario);

        // ASSERT
        assertNotNull(funcionario);
        assertEquals(3, funcionario.getIdFuncionario());
        assertEquals(runFaker, funcionario.getRun());
        assertEquals(dvFaker, funcionario.getDvrun());
        assertEquals(pNombreFaker, funcionario.getPNombre());
        assertEquals(sNombreFaker, funcionario.getSNombre());
        assertEquals(apPaternoFaker, funcionario.getApPaterno());
        assertEquals(apMaternoFaker, funcionario.getApMaterno());
        assertEquals(emailFaker, funcionario.getEmail());
    }
}
