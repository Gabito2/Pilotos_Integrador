package org.example.data.util;

import org.example.data.entity.PilotData;
import piloto.modelo.Pilot;

public class Mapper {
    public static PilotData fromDomain(Pilot pilot){
        return new PilotData(pilot.getUuid(), pilot.getName(), pilot.getSurname(), pilot.getFullName(), pilot.getShortName(), pilot.getPictureUrl());
    }

    public static Pilot fromData(PilotData pilot){
        return Pilot.InstanciaPilot(pilot.getUuid(), pilot.getName(), pilot.getSurname(), pilot.getFullName(), pilot.getShortName(), pilot.getPictureUrl());
    }
}
