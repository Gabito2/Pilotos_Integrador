package piloto.input;

import piloto.modelo.Pilot;

import java.util.ArrayList;

public interface SearchPilotInput {
    ArrayList<Pilot> getPilots();
    ArrayList<Pilot> searchPilotByName(String name);
    ArrayList<Pilot> searchPilotByShortName(String shortName);
    ArrayList<Pilot> searchPilotByFullName(String fullName);
}
