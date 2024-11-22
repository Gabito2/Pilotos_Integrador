package org.example.servicios.domain;

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

    public PilotDTO() {}

    public PilotDTO(String driver_number, String broadcast_name, String full_name, String name_acronym, String team_name, String team_colour, String first_name, String headshot_url, String last_name, String country_code, String session_key, String meeting_key) {
        this.driver_number = driver_number;
        this.broadcast_name = broadcast_name;
        this.full_name = full_name;
        this.name_acronym = name_acronym;
        this.team_name = team_name;
        this.team_colour = team_colour;
        this.first_name = first_name;
        this.headshot_url = headshot_url;
        this.last_name = last_name;
        this.country_code = country_code;
        this.session_key = session_key;
        this.meeting_key = meeting_key;
    }

    public String  getDriver_number() {
        return driver_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getBroadcast_name() {
        return broadcast_name;
    }

    public String getName_acronym() {
        return name_acronym;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getTeam_colour() {
        return team_colour;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getHeadshot_url() {
        return headshot_url;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getSession_key() {
        return session_key;
    }

    public String getMeeting_key() {
        return meeting_key;
    }
}