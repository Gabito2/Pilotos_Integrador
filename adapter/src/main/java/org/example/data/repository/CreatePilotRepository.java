package org.example.data.repository;

import org.example.data.dbAPI.PilotRepository;
import org.example.data.util.Mapper;
import org.springframework.stereotype.Service;
import piloto.modelo.Pilot;
import piloto.output.CreatePilotOutPut;

@Service
public class CreatePilotRepository implements CreatePilotOutPut {
    private final PilotRepository pilotRepository;

    public CreatePilotRepository(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    //Se mapea de un Pilot a un PilotData
    @Override
    public Pilot createPiloto(Pilot pilot) {
        return Mapper.fromData(pilotRepository.save(Mapper.fromDomain(pilot)));
    }

    @Override
    public boolean existPilotByFullName(String fullName) {
        return false;
    }

    @Override
    public boolean existPilotByShortName(String shortName) {
        return pilotRepository.existsByShortName(shortName);
    }
}
