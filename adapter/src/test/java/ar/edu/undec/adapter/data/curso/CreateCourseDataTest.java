package ar.edu.undec.adapter.data.curso;

import org.example.data.dbAPI.PilotRepository;
import org.example.data.entity.PilotData;
import org.example.data.repository.CreatePilotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exceptions.ExceptionPilot;
import piloto.modelo.Pilot;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCourseDataTest {

    @Mock
    PilotRepository pilotRepository;

    @InjectMocks
    CreatePilotRepository createPilotRepository;  // Reemplazar con el correcto nombre de tu repositorio

    @Test
    public void saveCourse_Course_Successful() {
        // Arrange: Crea un piloto de prueba
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");

        // Simula que la operación de guardado es exitosa
        when(pilotRepository.save(org.mockito.ArgumentMatchers.any(PilotData.class)))
                .thenReturn(new PilotData());

        // Act: Verifica el resultado de la creación del piloto
        Pilot result = createPilotRepository.createPiloto(pilot);

        // Assert
        Assertions.assertTrue(result instanceof Pilot);
    }


    @Test
    public void saveCourse_Course_returnFalse() {
        // Crea un piloto de prueba
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");

        // Simula que el repositorio lanza una excepción al intentar guardar
        doThrow(new ExceptionPilot("Error al guardar piloto"))
                .when(pilotRepository).save(org.mockito.ArgumentMatchers.any(PilotData.class));

        // Ejecuta el método y maneja la excepción
        Exception exception = Assertions.assertThrows(ExceptionPilot.class, () -> {
            createPilotRepository.createPiloto(pilot);
        });

        // Verifica el mensaje de la excepción
        Assertions.assertEquals("Error al guardar piloto", exception.getMessage());
    }


}