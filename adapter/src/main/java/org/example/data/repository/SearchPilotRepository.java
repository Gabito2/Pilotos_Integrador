package org.example.data.repository;

import org.example.data.dbAPI.PilotRepository;
import org.example.data.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piloto.modelo.Pilot;
import piloto.output.SearchPilotOutPut;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SearchPilotRepository implements SearchPilotOutPut {

    PilotRepository pilotRepository;

    @Autowired
    public SearchPilotRepository(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public ArrayList<Pilot> getPilots() {
        return (ArrayList<Pilot>) pilotRepository.findAll().stream().map(Mapper::fromData).collect(Collectors.toList());
    }

    @Override
    public ArrayList<Pilot> searchPilotByName() {
        return null;
    }

    @Override
    public ArrayList<Pilot> searchPilotByShort_name() {
        return null;
    }

    @Override
    public ArrayList<Pilot> searchPilotByFull_name() {
        return null;
    }
}
