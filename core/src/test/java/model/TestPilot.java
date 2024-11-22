package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piloto.exceptions.ExceptionPilot;
import piloto.modelo.Pilot;

import java.util.UUID;

public class TestPilot {
    @Test
    public void createPilot(){
        Pilot pilot = Pilot.InstanciaPilot(UUID.randomUUID(),"Franco", "Colapinto", "Colpainto Franco", "COL", "url");
        Assertions.assertNotNull(pilot);
    }

    @Test
    public void createPilot_nameEmpty(){
        RuntimeException excepcion = Assertions.assertThrows(ExceptionPilot.class, ()->Pilot.InstanciaPilot(UUID.randomUUID(),"", "Colapinto", "Colpainto Franco", "COL", "url"));
        Assertions.assertEquals("Error, datos del piloto vacios ...", excepcion.getMessage());
    }

    @Test
    public void createPilot_short_nameEmpty(){
        RuntimeException excepcion = Assertions.assertThrows(ExceptionPilot.class, ()->Pilot.InstanciaPilot(UUID.randomUUID(),"Franco", "Colapinto", "Colpainto Franco", "", "url"));
        Assertions.assertEquals("Error, datos del piloto vacios ...", excepcion.getMessage());
    }

    @Test
    public void createPilot_full_name_Null(){
        RuntimeException excepcion = Assertions.assertThrows(ExceptionPilot.class, ()->Pilot.InstanciaPilot(UUID.randomUUID(),"Franco", "Colapinto", null, "COL", "url"));
        Assertions.assertEquals("Error, datos del piloto nulos ...", excepcion.getMessage());
    }

    @Test
    public void createPilot_id_Null(){
        RuntimeException excepcion = Assertions.assertThrows(ExceptionPilot.class, ()->Pilot.InstanciaPilot(null,"Franco", "Colapinto", null, "COL", "url"));
        Assertions.assertEquals("Error, datos del piloto nulos ...", excepcion.getMessage());
    }

    @Test
    public void createPilot_picture_Null(){
        RuntimeException exception = Assertions.assertThrows(ExceptionPilot.class, () ->Pilot.InstanciaPilot(UUID.randomUUID(),"Franco", "Colapinto", "Colapinto  Franco", "COL", null));
        Assertions.assertEquals("Error, datos del piloto nulos ...", exception.getMessage());
    }
}
