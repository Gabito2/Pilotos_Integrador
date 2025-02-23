package org.example.servicios.boostraping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import piloto.input.CreatePilotInput;
import piloto.input.SearchPilotInput;
import piloto.output.CreatePilotOutPut;
import piloto.output.SearchPilotOutPut;
import piloto.usecase.CreatePilotUseCase;
import piloto.usecase.SearchPilotUseCase;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreatePilotInput createPilotInput(CreatePilotOutPut createPilotOutPut) {
        return new CreatePilotUseCase(createPilotOutPut);
    }

    @Bean
    public SearchPilotInput searchPilotInput(SearchPilotOutPut searchPilotOutPut) {
        return new SearchPilotUseCase(searchPilotOutPut);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
