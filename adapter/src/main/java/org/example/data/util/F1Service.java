package org.example.data.util;

import org.example.servicios.domain.PilotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class F1Service {
    private final RestTemplate restTemplate;

    @Autowired
    public F1Service(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<PilotDTO> getPilotos() {
        // Bloqueamos para esperar el resultado de la llamada asíncrona
        // La URL de la API que deseas consultar
        String url = "https://api.openf1.org/v1/drivers";

        // Realizamos la solicitud GET y deserializamos la respuesta JSON en una lista de PilotoResponse
        ResponseEntity<List<PilotDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PilotDTO>>() {}
        );

        return response.getBody();  // Retorna la lista de pilotos
    }

}
