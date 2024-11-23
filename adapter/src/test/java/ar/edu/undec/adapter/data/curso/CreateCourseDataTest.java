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
    CreatePilotRepository createPilotRepository;

    @Test
    public void saveCourse_Course_Successful() {
        // Arrange
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");

        when(pilotRepository.save(org.mockito.ArgumentMatchers.any(PilotData.class)))
                .thenReturn(new PilotData());

        // Act
        Pilot result = createPilotRepository.createPiloto(pilot);

        // Assert
        Assertions.assertTrue(result instanceof Pilot);
    }


    @Test
    public void saveCourse_Course_returnFalse() {

        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");

        doThrow(new ExceptionPilot("Error al guardar piloto"))
                .when(pilotRepository).save(org.mockito.ArgumentMatchers.any(PilotData.class));

        Exception exception = Assertions.assertThrows(ExceptionPilot.class, () -> {
            createPilotRepository.createPiloto(pilot);
        });

        Assertions.assertEquals("Error al guardar piloto", exception.getMessage());
    }


}