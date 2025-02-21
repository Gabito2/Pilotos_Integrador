package ar.edu.undec.adapter.data.curso;

import org.example.data.dbAPI.PilotRepository;
import org.example.data.entity.PilotData;
import org.example.data.repository.CreatePilotRepository;
import org.example.data.util.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exceptions.ExceptionPilot;
import piloto.modelo.Pilot;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePilotDataTest {

    @Mock
    PilotRepository pilotRepository;

    @InjectMocks
    CreatePilotRepository createPilotRepository;

    @Test
    public void savePilot_Successful() {
        UUID id = UUID.randomUUID();
        Pilot pilot = Pilot.InstanciaPilot(id, "Franco", "Colapinto", "Colapinto Franco", "COL", "url");
        PilotData pilotData = Mapper.fromDomain(pilot);  
        
        when(pilotRepository.save(any(PilotData.class))).thenReturn(pilotData);
        
        Pilot result = createPilotRepository.createPiloto(pilot);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(pilot.getUuid(), result.getUuid());
        Assertions.assertEquals(pilot.getName(), result.getName());
        Assertions.assertEquals(pilot.getSurname(), result.getSurname());
        Assertions.assertEquals(pilot.getFullName(), result.getFullName());
        Assertions.assertEquals(pilot.getShortName(), result.getShortName());
        Assertions.assertEquals(pilot.getPictureUrl(), result.getPictureUrl());
    }


    @Test
    public void savePilot_returnFalse() {

        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url");

        doThrow(new ExceptionPilot("Error al guardar piloto"))
                .when(pilotRepository).save(org.mockito.ArgumentMatchers.any(PilotData.class));

        Exception exception = Assertions.assertThrows(ExceptionPilot.class, () -> {
            createPilotRepository.createPiloto(pilot);
        });

        Assertions.assertEquals("Error al guardar piloto", exception.getMessage());
    }

}