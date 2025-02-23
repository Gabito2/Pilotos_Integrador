package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exceptions.ExceptionPilot;
import piloto.input.CreatePilotInput;
import piloto.modelo.Pilot;
import piloto.output.CreatePilotOutPut;
import piloto.usecase.CreatePilotUseCase;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TestCreatePilotUseCase {

    CreatePilotInput createPilotInput;

    @Mock
    CreatePilotOutPut createPilotOutPut;

    @InjectMocks
    private CreatePilotUseCase createPilotUseCase;

    private UUID uuid = UUID.randomUUID();

    @Test
    public void testCreatePilot() {

        Pilot pilot = Pilot.InstanciaPilot(uuid, "Franco", "Colapinto", "Colpainto Franco", "COL", "url");
        when(createPilotOutPut.existPilotByFullName("Colpainto Franco")).thenReturn(false);
        when(createPilotOutPut.existPilotByShortName("COL")).thenReturn(false);

        when(createPilotOutPut.createPiloto(pilot)).thenReturn(pilot);

        Pilot pilotoRecibido = createPilotUseCase.createPilot(pilot);

        Assertions.assertEquals(pilot, pilotoRecibido);
    }

    @Test
    public void testCreatePilot_ExistPilot() {
        Pilot pilot = Pilot.InstanciaPilot(uuid, "Franco", "Colapinto", "Colpainto Franco", "COL", "url");
        when(createPilotOutPut.existPilotByFullName("Colpainto Franco")).thenReturn(true);
        //when(createPilotOutPut.existPilotByShortName("COL")).thenReturn(true);

        Assertions.assertThrows(Exception.class, () -> createPilotUseCase.createPilot(pilot));

    }

    @Test
    public void testCreatePilot_Error() {
        Pilot pilot = Pilot.InstanciaPilot(uuid, "Franco", "Colapinto", "Colpainto Franco", "COL", "url");
        when(createPilotOutPut.existPilotByFullName("Colpainto Franco")).thenReturn(false);
        when(createPilotOutPut.existPilotByShortName("COL")).thenReturn(false);

        when(createPilotUseCase.createPilot(pilot)).thenThrow(new ExceptionPilot("Error al cargar el piloto"));

        ExceptionPilot exceptionPilot = Assertions.assertThrows(ExceptionPilot.class, () -> createPilotUseCase.createPilot(pilot));
        Assertions.assertEquals("Error al cargar el piloto", exceptionPilot.getMessage());

    }

}
