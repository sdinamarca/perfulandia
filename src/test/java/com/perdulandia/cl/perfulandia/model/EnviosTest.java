package com.perdulandia.cl.perfulandia.model;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.repository.EnviosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Date;

@DataJpaTest
public class EnviosTest {

    @Autowired
    private EnviosRepository enviosRepository;

    @Test
    public void testGuardarEnvioExitoso() {
        Envios envio = new Envios();
        envio.setFechaEnvio(LocalDate.now());
        envio.setNombrePedido("Pedido #123");
        envio.setSeguimiento("ABC123456789");
        envio.setDireccion("Av. Principal 123, Ciudad");
        envio.setEstado("procesando");

        Envios guardado = enviosRepository.save(envio);

        Assertions.assertNotNull(guardado.getIdPedido());
        Assertions.assertEquals("Pedido #123", guardado.getNombrePedido());
    }

    @Test
    public void testGuardarEnvioSinNombrePedidoDebeFallar() {
        Envios envio = new Envios();
        envio.setFechaEnvio(LocalDate.now());
        envio.setNombrePedido(null); // Violación de not null
        envio.setSeguimiento("ABC123456789");
        envio.setDireccion("Av. Principal 123, Ciudad");
        envio.setEstado("enviado");

        Assertions.assertThrows(Exception.class, () -> {
            enviosRepository.saveAndFlush(envio);
        });
    }

    @Test
    public void testGuardarEnvioSinDireccionDebeFallar() {
        Envios envio = new Envios();
        envio.setFechaEnvio(LocalDate.now());
        envio.setNombrePedido("Pedido sin dirección");
        envio.setSeguimiento("XYZ987654321");
        envio.setDireccion(null); // Violación de not null
        envio.setEstado("enviado");

        Assertions.assertThrows(Exception.class, () -> {
            enviosRepository.saveAndFlush(envio);
        });
    }
}
