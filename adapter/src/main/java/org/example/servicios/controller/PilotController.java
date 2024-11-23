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
    public ResponseEntity<?> saveUpdate() {
        try {
            // Lista de pilotos obtenida desde el servicio
            List<PilotDTO> pilotDTOS = f1Service.getPilotos();

            // Validar duplicados
            Set<String> nombresRegistrados = new HashSet<>();
            for (PilotDTO pilotDTO : pilotDTOS) {
                String short_name = pilotDTO.getName_acronym();

                // Si ya existe un nombre corto duplicado, devolvemos un error
                if (nombresRegistrados.contains(short_name)) {
                    return ResponseEntity.badRequest().body("Pilotos duplicados encontrados: " + short_name);
                }

                nombresRegistrados.add(short_name);
            }

            // Crear pilotos si no hay duplicados
            for (PilotDTO pilotDTO : pilotDTOS) {
                if (pilotDTO.getFirst_name() != null && pilotDTO.getLast_name() != null && pilotDTO.getFull_name() != null &&
                        pilotDTO.getName_acronym() != null && pilotDTO.getHeadshot_url() != null) {
                    createPilotInput.createPilot(
                            Pilot.InstanciaPilot(UUID.randomUUID(),
                                    pilotDTO.getFirst_name(),
                                    pilotDTO.getLast_name(),
                                    pilotDTO.getFull_name(),
                                    pilotDTO.getName_acronym(),
                                    pilotDTO.getHeadshot_url())
                    );
                }
            }

            return ResponseEntity.created(null).body("Pilotos creados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar pilotos: " + e.getMessage());
        }
    }

}

