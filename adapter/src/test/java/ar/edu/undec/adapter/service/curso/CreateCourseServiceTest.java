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
        // Arrange
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");
        List<PilotDTO> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotDTO(null, null, "Colapinto Franco", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));

        when(f1Service.getPilotos()).thenReturn(mockPilotList);
        when(createPilotInput.createPilot(any(Pilot.class))).thenReturn(pilot);

        // Act
        ResponseEntity<?> result = pilotController.saveUpdate();

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void createCourse_courseExists_Return400() throws Exception {
        // Arrange
        List<PilotDTO> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotDTO(null, null, "Colapinto Franco", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));
        mockPilotList.add(new PilotDTO(null, null, "Colapinto Franco", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));

        when(f1Service.getPilotos()).thenReturn(mockPilotList);

        // Act
        ResponseEntity<?> result = pilotController.saveUpdate();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void createCourse_courseNoSaved_Return500() throws Exception {
        // Arrange
        List<PilotDTO> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotDTO(null, null, "Colapinto Franco", "COL", null, null, "Franco", "url", "Colapinto", null, null, null));

        when(f1Service.getPilotos()).thenReturn(mockPilotList);
        when(createPilotInput.createPilot(any(Pilot.class))).thenThrow(new RuntimeException("Error al guardar el piloto"));

        // Act
        ResponseEntity<?> result = pilotController.saveUpdate();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
