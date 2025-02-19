package ar.edu.undec.adapter.service.curso;

import org.example.servicios.controller.PilotController;
import org.example.servicios.domain.PilotDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import piloto.input.CreatePilotInput;
import piloto.modelo.Pilot;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCourseServiceTest {

    @Mock
    CreatePilotInput createPilotInput; 

    @Mock
    org.example.data.util.F1Service f1Service;

    @InjectMocks
    PilotController pilotController;

    @Test
    public void createCourse_courseSaver_Return201() throws Exception {
        List<PilotDTO> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotDTO(null, null, "Colapinto Franco", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));

        when(f1Service.getPilotos()).thenReturn(mockPilotList);

        ResponseEntity<?> result = pilotController.saveUpdate();

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(createPilotInput, times(1)).createPilot(any(Pilot.class));
    }

    @Test
    public void createCourse_courseExists_Return409() throws Exception {
        List<PilotDTO> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotDTO(null, null, "Franco Colapinto", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));
        mockPilotList.add(new PilotDTO(null, null, "Franco Colapinto", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));

        when(f1Service.getPilotos()).thenReturn(mockPilotList);

        ResponseEntity<?> result = pilotController.saveUpdate();

        Assertions.assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        Assertions.assertTrue(result.getBody().toString().contains("Error al cargar los pilotos."));
    }

    @Test
    public void createCourse_serviceError_Return500() throws Exception {
        when(f1Service.getPilotos()).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> result = pilotController.saveUpdate();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertTrue(result.getBody().toString().contains("Internal Server Error"));
    }
}
