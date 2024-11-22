package org.example.data.dbAPI;

import org.example.data.entity.PilotData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface PilotRepository extends JpaRepository<PilotData, UUID> {
    PilotData save(PilotData pilotData);
    boolean existsByShortName(String shortName);
    ArrayList<PilotData> findAllByName(String name);
    ArrayList<PilotData> findAll();
}
