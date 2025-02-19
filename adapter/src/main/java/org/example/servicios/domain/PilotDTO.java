package org.example.servicios.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PilotDTO {
    private String driver_number;
    private String broadcast_name;
    private String full_name;
    private String name_acronym;
    private String team_name;
    private String team_colour;
    private String first_name;
    private String last_name;
    private String headshot_url;
    private String country_code;
    private String session_key;
    private String meeting_key;

}