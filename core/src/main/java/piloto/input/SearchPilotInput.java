package piloto.input;

import piloto.modelo.Pilot;

import java.util.ArrayList;

public interface SearchPilotInput {
    ArrayList<Pilot> getPilots();
    ArrayList<Pilot> searchPilotByName();
    ArrayList<Pilot> searchPilotByShort_name();
    ArrayList<Pilot> searchPilotByFull_name();
}
