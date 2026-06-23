# Plan de Pruebas - Sanos y Salvos

## Objetivo

Validar el correcto funcionamiento de los distintos microservicios mediante pruebas unitarias enfocadas en la persistencia e inserción de datos (`inserts`), utilizando JUnit y la generación de datos ficticios con DataFaker.

---

## Herramientas

- Java 21
- Spring Boot
- JUnit 5
- DataFaker

---

# Casos de Prueba

## CP-01: Registrar Mascota

### Método
[cite_start]Creación de objeto `Mascota` [cite: 250]

### Objetivo
[cite_start]Verificar que una mascota pueda registrarse correctamente asignando su nombre de manera explícita[cite: 251].

### Resultado Esperado
* [cite_start]El objeto Mascota debe existir[cite: 252].
* [cite_start]El nombre de la mascota debe coincidir exactamente con "Mascota generica 2"[cite: 251, 252].

---

## CP-02: Registrar Dueño

### Método
[cite_start]Creación de objeto `Duenio` [cite: 199]

### Objetivo
[cite_start]Validar la creación de un dueño de mascota asegurando la concordancia de datos dinámicos generados por Faker[cite: 200, 203].

### Resultado Esperado
* [cite_start]El objeto Duenio debe existir en el sistema[cite: 203].
* [cite_start]El ID del dueño debe ser igual a 5[cite: 204].
* [cite_start]El primer nombre (`pNombre`) debe coincidir con el valor dinámico generado por la prueba[cite: 200, 203].

---

## CP-03: Registrar Tipo de Institución

### Método
[cite_start]Creación de objeto `TipoInstitucion` [cite: 205]

### Objetivo
[cite_start]Verificar el correcto registro de un tipo de institución usando descripciones aleatorias de compañías y educación[cite: 206, 211].

### Resultado Esperado
* [cite_start]El objeto TipoInstitucion debe crearse con éxito[cite: 208].
* [cite_start]La descripción almacenada no debe ser nula y debe corresponder a la asignada[cite: 208, 213].
* [cite_start]El identificador del tipo de institución debe ser válido y no nulo[cite: 209, 214].

---

## CP-04: Registrar Funcionario (Personal)

### Método
[cite_start]Creación de objeto `Funcionario` [cite: 293]

### Objetivo
[cite_start]Validar que todos los campos críticos de la entidad Funcionario (RUN, nombres, apellidos, email) se mapeen correctamente de manera dinámica[cite: 294, 295, 296].

### Resultado Esperado
* [cite_start]El objeto Funcionario debe ser instanciado y sus atributos validados[cite: 297].
* [cite_start]El ID asignado debe ser igual a 3[cite: 297].
* [cite_start]Los datos dinámicos (RUN, DV, nombres, apellidos, email) deben coincidir plenamente con los generados por Faker[cite: 297, 298].

---

## CP-05: Registrar Localización (Provincia)

### Método
[cite_start]Creación de objeto `Provincia` [cite: 234]

### Objetivo
[cite_start]Verificar la asignación e inserción del nombre de una provincia específica en el sistema[cite: 235].

### Resultado Esperado
* [cite_start]El objeto Provincia debe ser creado correctamente[cite: 236].
* [cite_start]El nombre de la provincia debe ser estrictamente "Provincia de Santiago"[cite: 236].

---

## CP-06: Registrar Taxonomía (Especie y Raza)

### Método
[cite_start]Creación de objetos `Especie` y `Raza`

### Objetivo
[cite_start]Validar la creación de una raza de mascota y su correcta asociación jerárquica con una especie determinada[cite: 335, 336].

### Resultado Esperado
* [cite_start]El objeto Raza debe existir con su respectivo identificador[cite: 336, 337].
* [cite_start]La raza debe contener una instancia válida de la entidad Especie asociada[cite: 337].
* [cite_start]Los nombres de la raza y de la especie deben coincidir con los valores provistos por Faker[cite: 337].

---

## CP-07: Registrar Salud (Historial Diagnóstico)

### Método
[cite_start]Creación de objeto `HistorialDiagnostico` [cite: 312, 327]

### Objetivo
[cite_start]Verificar el registro clínico de una atención médica vinculando el RUT del funcionario y el ID de la mascota.

### Resultado Esperado
* [cite_start]El objeto HistorialDiagnostico debe crearse exitosamente en el sistema[cite: 329].
* [cite_start]Las observaciones generadas dinámicamente deben quedar registradas de forma consistente.

---

## CP-08: Registrar Alerta

### Método
[cite_start]Creación de objeto `Alerta` [cite: 173]

### Objetivo
[cite_start]Validar la configuración correcta de los medios de notificación autorizados ("Telefono", "sms", "correo") en el objeto de alerta[cite: 173, 174, 176].

### Resultado Esperado
* [cite_start]El objeto Alerta debe inicializarse correctamente[cite: 174].
* [cite_start]El canal de comunicación o medio de notificación asignado debe coincidir con el valor definido en la prueba[cite: 174, 175, 177].

---

## CP-09: Registrar Reporte

### Método
[cite_start]Creación de objeto `Reporte` [cite: 302]

### Objetivo
[cite_start]Verificar la inserción de un reporte de suceso con un estado inicial por defecto controlado como "PENDIENTE"[cite: 303].

### Resultado Esperado
* [cite_start]El objeto Reporte debe persistirse sin problemas[cite: 304].
* [cite_start]El tipo de reporte configurado debe coincidir con la cadena establecida ("Reporte generico 2")[cite: 303, 305].

---

## CP-10: Registrar Auditoría

### Método
[cite_start]Creación de objeto `Auditoria` [cite: 178]

### Objetivo
[cite_start]Asegurar que los registros de eventos de auditoría capturen la acción simulada por Faker y almacenen el resultado del proceso ("EXITOSO").

### Resultado Esperado
* [cite_start]La entidad Auditoria debe ser creada correctamente.
* [cite_start]El atributo `pNombre` debe corresponder de forma exacta a la etiqueta asignada.

---

## Matriz de Resumen de Casos de Prueba

| ID    | Caso de Prueba             | Objetivo                                                             | Resultado Esperado                                                                   | Tipo     |
| ----- | -------------------------- | -------------------------------------------------------------------- | ------------------------------------------------------------------------------------ | -------- |
| CP-01 | Registrar Mascota          | Validar creación de mascota con nombre explícito.                    | La mascota se crea correctamente bajo la denominación "Mascota generica 2".          | Unitaria |
| CP-02 | Registrar Dueño            | Validar la persistencia de un dueño con datos correlacionados.       | El dueño existe con ID 5 y coincide con el nombre dinámico de Faker.                 | Unitaria |
| CP-03 | Registrar Tipo Institución | Validar la creación estructural de categorías de institución.        | El tipo se genera con una descripción válida no nula y un ID consistente.            | Unitaria |
| CP-04 | Registrar Funcionario      | Validar el mapeo integral de campos de un personal/funcionario.      | El funcionario se inicializa con ID 3 emparejando todos sus datos dinámicos.         | Unitaria |
| CP-05 | Registrar Localización     | Validar la especificación geográfica a nivel de provincia.           | La provincia se inserta exitosamente llamándose "Provincia de Santiago".             | Unitaria |
| CP-06 | Registrar Taxonomía        | Validar la integridad referencial entre una Raza y su Especie.       | La raza queda registrada manteniendo el vínculo directo a una especie válida.        | Unitaria |
| CP-07 | Registrar Salud            | Validar la creación de una bitácora médica o historial de atención.  | El historial clínico se inicializa asociándose al funcionario y mascota requerida.   | Unitaria |
| CP-08 | Registrar Alerta           | Validar los canales de notificación habilitados en alertas.          | La alerta se genera reconociendo explícitamente "Telefono", "sms" o "correo".        | Unitaria |
| CP-09 | Registrar Reporte          | Validar el ingreso de reportes de incidentes en estado pendiente.    | El reporte se guarda con el tipo asignado y el estado inicial esperado.               | Unitaria |
| CP-10 | Registrar Auditoría        | Validar el guardado de transacciones de control en el sistema.       | La auditoría se inserta reflejando el estado de la acción y el nombre definido.      | Unitaria |