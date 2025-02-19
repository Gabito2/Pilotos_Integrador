package org.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
