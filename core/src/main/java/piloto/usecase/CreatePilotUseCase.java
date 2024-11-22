package piloto.usecase;

import piloto.exceptions.ExceptionPilot;
import piloto.input.CreatePilotInput;
import piloto.modelo.Pilot;
import piloto.output.CreatePilotOutPut;

public class CreatePilotUseCase implements CreatePilotInput {
    private final CreatePilotOutPut createPilotOutPut;

    public CreatePilotUseCase(CreatePilotOutPut createPilotOutPut) {
        this.createPilotOutPut = createPilotOutPut;
    }

    @Override
    public Pilot createPilot(Pilot pilot) {

        if (createPilotOutPut.existPilotByFullName(pilot.getFullName()) || createPilotOutPut.existPilotByShortName(pilot.getShortName())) {
            throw new ExceptionPilot("El piloto ya existe");
        }

        return createPilotOutPut.createPiloto(pilot);
    }


}
