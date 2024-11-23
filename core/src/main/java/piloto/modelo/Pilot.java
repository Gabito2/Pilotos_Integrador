package piloto.modelo;

import piloto.exceptions.ExceptionPilot;

import java.util.UUID;

public class Pilot {
    private UUID uuid;
    private String name;
    private String surname;
    private String fullName;
    private String shortName;
    private String pictureUrl;

    private Pilot(UUID uuid, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }

    public static Pilot InstanciaPilot(UUID uuid, String name,
                                       String surname,
                                       String fullName,
                                       String shortName,
                                       String pictureUrl) throws ExceptionPilot {
//        if (uuid == null || name == null || surname == null || fullName == null || shortName == null || pictureUrl == null) {
//            throw new ExceptionPilot("Error, datos del piloto nulos ...");
//        }
//        if (name.isEmpty() || surname.isEmpty() || fullName.isEmpty() || shortName.isEmpty()) {
//            throw new ExceptionPilot("Error, datos del piloto vacios ...");
//        }

        return new Pilot(uuid, name, surname, fullName, shortName, pictureUrl);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}