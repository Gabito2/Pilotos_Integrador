package org.example.data.dbAPI;

import org.example.data.entity.PilotData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface PilotRepository extends CrudRepository<PilotData, UUID> {
    PilotData save(PilotData pilotData);
    boolean existsByShortName(String shortName);
    boolean existsPilotByFullName(String fullName);
    ArrayList<PilotData> findAllByName(String name);
    ArrayList<PilotData> findAll();
}
