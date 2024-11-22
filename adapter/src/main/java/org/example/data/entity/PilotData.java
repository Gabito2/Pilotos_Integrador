package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_pilot")

public class PilotData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID uuid;
    private String name;
    private String surname;
    private String fullName;
    private String shortName;
    private String pictureUrl;

    public PilotData() {}

    public PilotData(UUID uuid, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }
}
