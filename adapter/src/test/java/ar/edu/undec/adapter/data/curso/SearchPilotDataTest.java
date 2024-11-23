package ar.edu.undec.adapter.data.curso;

import org.example.data.dbAPI.PilotRepository;
import org.example.data.entity.PilotData;
import org.example.data.repository.SearchPilotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.modelo.Pilot;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPilotRepositoryTest {

    @Mock
    private PilotRepository pilotRepository;

    @InjectMocks
    private SearchPilotRepository searchPilotRepository;

    @Test
    void getPilots_ReturnsListOfPilots() {
        ArrayList<PilotData> mockPilotList = new ArrayList<>();
        mockPilotList.add(new PilotData(UUID.randomUUID(), "Sergio", "Perez", "Sergio PEREZ", "PER", "url1"));
        mockPilotList.add(new PilotData(UUID.randomUUID(), "Guanyu", "Zhouv", "ZHOU Guanyu", "ZHO", "url2"));

        when(pilotRepository.findAll()).thenReturn(mockPilotList);

        ArrayList<Pilot> pilots = searchPilotRepository.getPilots();

        assertNotNull(pilots);
        assertEquals(2, pilots.size());
        assertEquals("Sergio", pilots.get(0).getName());
    }


    @Test
    void getPilots_ReturnEmpty() {
        when(pilotRepository.findAll()).thenReturn(new ArrayList<>());

        ArrayList<Pilot> pilots = searchPilotRepository.getPilots();

        assertNotNull(pilots);
        assertTrue(pilots.isEmpty());
    }

}
