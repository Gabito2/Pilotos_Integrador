package piloto.usecase;

import piloto.input.SearchPilotInput;
import piloto.modelo.Pilot;
import piloto.output.SearchPilotOutPut;

import java.util.ArrayList;

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
    public ArrayList<Pilot> searchPilotByName() {
        return searchPilotOutPut.searchPilotByName();
    }

    @Override
    public ArrayList<Pilot> searchPilotByShort_name(){
        return searchPilotOutPut.searchPilotByShort_name();
    }

    @Override
    public ArrayList<Pilot> searchPilotByFull_name(){
        return searchPilotOutPut.searchPilotByFull_name();
    }

}
