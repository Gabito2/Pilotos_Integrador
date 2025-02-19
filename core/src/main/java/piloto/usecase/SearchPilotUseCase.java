package piloto.usecase;

import piloto.input.SearchPilotInput;
import piloto.modelo.Pilot;
import piloto.output.SearchPilotOutPut;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchPilotUseCase implements SearchPilotInput {
    private SearchPilotOutPut searchPilotOutPut;

    public SearchPilotUseCase(SearchPilotOutPut searchPilotOutPut) {
        this.searchPilotOutPut = searchPilotOutPut;
    }

    @Override
    public ArrayList<Pilot> getPilots() {
        return searchPilotOutPut.getPilots();
    }

    @Override
    public ArrayList<Pilot> searchPilotByName(String name) {
        return new ArrayList<>(searchPilotOutPut.searchPilotByName(name).stream().filter(pilot -> pilot.getName().equals(name)).collect(Collectors.toList()));
    }

    @Override
    public ArrayList<Pilot> searchPilotByShortName(String shortName) {
        return new ArrayList<>(searchPilotOutPut.searchPilotByShortName(shortName).stream().filter(pilot -> pilot.getShortName().equals(shortName)).collect(Collectors.toList()));
    }

    @Override
    public ArrayList<Pilot> searchPilotByFullName(String fullName) {
        return new ArrayList<>(searchPilotOutPut.searchPilotByFullName(fullName).stream().filter(pilot -> pilot.getFullName().equals(fullName)).collect(Collectors.toList()));
    }

}
