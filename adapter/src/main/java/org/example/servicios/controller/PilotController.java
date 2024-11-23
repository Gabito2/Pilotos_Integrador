package org.example.servicios.controller;

import org.example.data.util.F1Service;
import org.example.servicios.domain.PilotDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/listar")
    public ArrayList<Pilot> getPilots() {
        return searchPilotInput.getPilots();
    }

    @GetMapping
    public ResponseEntity<?> saveUpdate() {
        Set<String> nombresRegistrados = new HashSet<>();
        List<PilotDTO> pilotDTOS = f1Service.getPilotos();
        List<String> errores = new ArrayList<>();

        for (PilotDTO pilotDTO : pilotDTOS) {
            String shortName = pilotDTO.getName_acronym();

            // Validar campos nulos o vacíos
            if (pilotDTO.getFirst_name() == null || pilotDTO.getLast_name() == null ||
                    pilotDTO.getFull_name() == null || shortName == null ||
                    pilotDTO.getHeadshot_url() == null ||
                    pilotDTO.getFirst_name().isEmpty() || pilotDTO.getLast_name().isEmpty() ||
                    pilotDTO.getFull_name().isEmpty() || shortName.isEmpty() ||
                    pilotDTO.getHeadshot_url().isEmpty()) {

                errores.add("Error: Datos incompletos para el piloto " + pilotDTO.getFull_name());
                continue; // Saltar este piloto y continuar con el siguiente
            }

            // Validar duplicados
            if (nombresRegistrados.contains(shortName)) {
                errores.add("Error: Piloto duplicado encontrado (" + shortName + ").");
                continue; // Saltar este piloto y continuar con el siguiente
            }

            // Registrar piloto
            nombresRegistrados.add(shortName);
            createPilotInput.createPilot(
                    Pilot.InstanciaPilot(UUID.randomUUID(),
                            pilotDTO.getFirst_name(),
                            pilotDTO.getLast_name(),
                            pilotDTO.getFull_name(),
                            shortName,
                            pilotDTO.getHeadshot_url())
            );
        }

        // Construir respuesta final
        if (!errores.isEmpty()) {
            return ResponseEntity.status(207).body("Pilotos registrados con errores: \n" + String.join("\n", errores));
        }

        return ResponseEntity.status(201).body("Se guardaron todos los pilotos correctamente.");
    }


}