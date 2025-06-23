package com.perdulandia.cl.perfulandia;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.repository.EnviosRepository;
import com.perdulandia.cl.perfulandia.service.ServiceEnvios;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@SpringBootTest
public class ServiceEnviosTest {

    @Autowired
    private ServiceEnvios serviceenvio;


    @MockBean
    private EnviosRepository enviosRepository;

    //test que prueba encontrar todos los envios existentes
    @Test
    public void testFindAll(){

        when(enviosRepository.findAll()).thenReturn(List.of(new Envios( 1L, LocalDate.of(2012, 12, 12), "a23fc2a", "asd123edq","Los alamos 1232","ENVIADO")));

        List<Envios> envios = serviceenvio.findAll();

        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testFindById() {
        Envios envio = new Envios(1L, LocalDate.of(2023, 6, 21), "codigoX", "trackingX", "direccionX", "PENDIENTE");
        when(enviosRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envios resultado = serviceenvio.findById(1L);

        assertNotNull(resultado);
    }

    @Test
    public void testSave() {
        Envios envio = new Envios(2L, LocalDate.of(2023, 6, 22), "codigoY", "trackingY", "direccionY", "ENVIADO");
        when(enviosRepository.save(envio)).thenReturn(envio);

        Envios resultado = serviceenvio.save(envio);

        assertNotNull(resultado);
    }

    @Test
    public void testDelete() {
        Long id = 3L;

        serviceenvio.delete(id);
    }
}
