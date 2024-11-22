package piloto.output;

import piloto.modelo.Pilot;

import java.util.ArrayList;

public interface SearchPilotOutPut {
    ArrayList<Pilot> getPilots();
    ArrayList<Pilot> searchPilotByName();
    ArrayList<Pilot> searchPilotByShort_name();
    ArrayList<Pilot> searchPilotByFull_name();
}
