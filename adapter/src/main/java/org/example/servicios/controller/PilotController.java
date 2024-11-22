package org.example.servicios.controller;

import org.example.data.util.F1Service;
import org.example.servicios.domain.PilotDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.input.CreatePilotInput;
import piloto.input.SearchPilotInput;
import piloto.modelo.Pilot;

import java.util.*;

@RestController
@RequestMapping(path = "drivers")
public class PilotController {

    private final CreatePilotInput createPilotInput;
    private final SearchPilotInput searchPilotInput;
    private final F1Service f1Service;

    public PilotController(CreatePilotInput createPilotInput, SearchPilotInput searchPilotInput, F1Service f1Service) {
        this.createPilotInput = createPilotInput;
        this.searchPilotInput = searchPilotInput;
        this.f1Service = f1Service;
    }

    @GetMapping("drivers/listar")
    public ArrayList<Pilot> getPilots() {
        return searchPilotInput.getPilots();
    }

    @GetMapping
    public ResponseEntity<?> saveUpdate()  {
        Set<String> nombresRegistrados = new HashSet<>();
        List <PilotDTO> pilotDTOS = f1Service.getPilotos();
        for (PilotDTO pilotDTO : pilotDTOS) {
            String short_name = pilotDTO.getName_acronym();

            // Verificamos si el nombre ya ha sido registrado
            if (!nombresRegistrados.contains(short_name)) {
                // Si no está registrado, lo agregamos al Set y lo registramos
                if (pilotDTO.getFirst_name() != null && pilotDTO.getLast_name() != null && pilotDTO.getLast_name() != null && pilotDTO.getName_acronym() != null && pilotDTO.getHeadshot_url() != null) {
                    nombresRegistrados.add(short_name);

                    // Registrar el piloto (puedes incluir más campos si lo necesitas)
                    createPilotInput.createPilot(
                            Pilot.InstanciaPilot(UUID.randomUUID(),
                                    pilotDTO.getFirst_name(),
                                    pilotDTO.getLast_name(),
                                    pilotDTO.getFull_name(),
                                    short_name,
                                    pilotDTO.getHeadshot_url())
                    );
                }

            }

        }
        return ResponseEntity.created(null).body("SE CREARON");

    }
}

