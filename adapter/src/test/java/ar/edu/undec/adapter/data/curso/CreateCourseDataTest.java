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
        // Crea un curso de prueba
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colpainto Franco", "COL", "url");

        // Simula que la operación de guardado es exitosa
        when(pilotRepository.save(any(PilotData))).thenReturn(new PilotData());

        // Verifica el resultado de la creación del curso
        Pilot result = createPilotRepository.createPiloto(pilot);
        Assertions.assertTrue(result instanceof Pilot);
    }

    @Test
    public void saveCourse_Course_returnFalse() {
        // Crea un curso de prueba
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colpainto Franco", "COL", "url");

        // Simula que la operación de guardado lanza una excepción
        when(pilotRepository.save(any(PilotData.class))).thenThrow(ExceptionPilot.class);
        // Verifica que la creación del curso retorna false en caso de error
//        boolean result = createPilotRepository.createCourse(course);
        Pilot result = createPilotRepository.createPiloto(pilot);
        Assertions.assertFalse(result instanceof Pilot);
    }
}