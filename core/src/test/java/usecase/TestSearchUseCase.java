package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.modelo.Pilot;
import piloto.output.SearchPilotOutPut;
import piloto.usecase.SearchPilotUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestSearchUseCase {

    @Mock
    SearchPilotOutPut searchPilotOutPut;

    @InjectMocks
    private SearchPilotUseCase searchPilotUseCase;

    @Test
    public void testSearchAllPilots() {
        List<Pilot> pilots = List.of(
                Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url1"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Lewis", "Hamilton", "Hamilton Lewis", "HAM", "url2"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Gabriel", "Rearte", "Rearte Gabriel", "GAB", "url3")
        );

        when(searchPilotOutPut.getPilots()).thenReturn(new ArrayList<>(pilots));

        ArrayList<Pilot> resultado = searchPilotUseCase.getPilots();

        Assertions.assertEquals(3, resultado.size());
        Assertions.assertEquals("Franco", resultado.get(0).getName());
        Assertions.assertEquals("Lewis", resultado.get(1).getName());
        Assertions.assertEquals("Gabriel", resultado.get(2).getName());

    }

    @Test
    public void testSearchPilotByFullName() {
        List<Pilot> pilots = List.of(
                Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url1"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Lewis", "Hamilton", "Hamilton Lewis", "HAM", "url2"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Gabriel", "Rearte", "Rearte Gabriel", "GAB", "url3")
        );
        when(searchPilotOutPut.searchPilotByFull_name()).thenReturn(new ArrayList<>(pilots));

        ArrayList<Pilot> result = searchPilotUseCase.searchPilotByFull_name();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("Colapinto Franco", result.get(0).getFullName());
    }

    @Test
    public void testSearchPilotByShortName() {
        List<Pilot> pilots = List.of(
                Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url1"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Lewis", "Hamilton", "Hamilton Lewis", "HAM", "url2"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Gabriel", "Rearte", "Rearte Gabriel", "GAB", "url3")
        );
        when(searchPilotOutPut.searchPilotByShort_name()).thenReturn(new ArrayList<>(pilots));

        ArrayList<Pilot> result = searchPilotUseCase.searchPilotByShort_name();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("COL", result.get(0).getShortName());
    }

    @Test
    public void testSearchPilotByName() {
        List<Pilot> pilots = List.of(
                Pilot.InstanciaPilot(UUID.randomUUID(), "Franco", "Colapinto", "Colapinto Franco", "COL", "url1"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Lewis", "Hamilton", "Hamilton Lewis", "HAM", "url2"),
                Pilot.InstanciaPilot(UUID.randomUUID(), "Gabriel", "Rearte", "Rearte Gabriel", "GAB", "url3")
        );
        when(searchPilotOutPut.searchPilotByName()).thenReturn(new ArrayList<>(pilots));

        ArrayList<Pilot> result = searchPilotUseCase.searchPilotByName();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("Franco", result.get(0).getName());
        Assertions.assertEquals("Gabriel", result.get(2).getName());
    }

}
