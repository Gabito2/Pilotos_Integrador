package org.example.servicios.controller;

import org.example.data.util.F1Service;
import org.example.servicios.domain.PilotDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piloto.input.CreatePilotInput;
import piloto.input.SearchPilotInput;
import piloto.modelo.Pilot;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/")
public class PilotController {

    private final CreatePilotInput createPilotInput;
    private final SearchPilotInput searchPilotInput;
    private final F1Service f1Service;

    public PilotController(CreatePilotInput createPilotInput, SearchPilotInput searchPilotInput, F1Service f1Service) {
        this.createPilotInput = createPilotInput;
        this.searchPilotInput = searchPilotInput;
        this.f1Service = f1Service;
    }

    @GetMapping("piltos")
    public ArrayList<Pilot> getPilots() {
        return searchPilotInput.getPilots();
    }

    @PostMapping("cargar")
    public ResponseEntity<?> saveUpdate() {
        try {
            Set<String> nombresRegistrados = new HashSet<>();
            List<PilotDTO> pilotDTOS = f1Service.getPilotos();
            List<String> errores = new ArrayList<>();
            int pilotosRegistrados = 0;

            for (PilotDTO pilotDTO : pilotDTOS) {
                String shortName = pilotDTO.getName_acronym();

                if (pilotDTO.getFirst_name() == null || pilotDTO.getLast_name() == null ||
                        pilotDTO.getFull_name() == null || shortName == null ||
                        pilotDTO.getHeadshot_url() == null ||
                        pilotDTO.getFirst_name().isEmpty() || pilotDTO.getLast_name().isEmpty() ||
                        pilotDTO.getFull_name().isEmpty() || shortName.isEmpty() ||
                        pilotDTO.getHeadshot_url().isEmpty()) {

                    continue;
                }

                if (nombresRegistrados.contains(shortName)) {
                    errores.add("Error: Piloto duplicado encontrado (" + shortName + ").");
                    continue;
                }

                nombresRegistrados.add(shortName);
                createPilotInput.createPilot(
                        Pilot.InstanciaPilot(UUID.randomUUID(),
                                pilotDTO.getFirst_name(),
                                pilotDTO.getLast_name(),
                                pilotDTO.getFull_name(),
                                shortName,
                                pilotDTO.getHeadshot_url())
                );
                pilotosRegistrados++;
            }

            if (!errores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }

            if (pilotosRegistrados > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Pilotos cargados con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se cargaron pilotos.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cargar los pilotos.");
        }
    }


}