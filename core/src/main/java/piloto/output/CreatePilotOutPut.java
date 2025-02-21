package piloto.output;

import piloto.modelo.Pilot;

public interface CreatePilotOutPut {
    Pilot createPiloto(Pilot pilot);
    boolean existPilotByFullName(String full_name);
    boolean existPilotByShortName(String short_name);

}

