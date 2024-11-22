package piloto.output;

import piloto.modelo.Pilot;

public interface CreatePilotOutPut {
//    boolean createPilot(String broadcast_name, String country_code, String driver_number,
//    String first_name, String full_name, String headshot_url,
//    String last_name, String meeting_key, String name_acronym,
//    String session_key, String team_colour, String team_name);

    Pilot createPiloto(Pilot pilot);

    boolean existPilotByFullName(String full_name);
    boolean existPilotByShortName(String short_name);

}
