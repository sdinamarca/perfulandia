package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.service.ServiceEnvios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnviosControllerTest {

    @Mock
    private ServiceEnvios serviceEnvios;

    @InjectMocks
    private EnviosController enviosController;

    private Envios envio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        envio = new Envios();
        envio.setIdPedido(1L);
        envio.setFechaEnvio(LocalDate.now());
        envio.setNombrePedido("Pedido de prueba");
        envio.setSeguimiento("TRK123456");
        envio.setDireccion("Calle Falsa 123");
        envio.setEstado("procesando");
    }

    @Test
    public void testListarEnvios() {
        List<Envios> lista = Arrays.asList(envio);
        when(serviceEnvios.findAll()).thenReturn(lista);

        ResponseEntity<List<Envios>> response = enviosController.listar();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(serviceEnvios, times(1)).findAll();
    }

    @Test
    public void testBuscarEnvioPorId() {
        when(serviceEnvios.findById(1L)).thenReturn(envio);

        ResponseEntity<Envios> response = enviosController.buscar(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pedido de prueba", response.getBody().getNombrePedido());
        verify(serviceEnvios).findById(1L);
    }

    @Test
    public void testGuardarEnvio() {
        when(serviceEnvios.save(any(Envios.class))).thenReturn(envio);

        ResponseEntity<Envios> response = enviosController.guardar(envio);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Pedido de prueba", response.getBody().getNombrePedido());
        verify(serviceEnvios).save(envio);
    }

    @Test
    public void testActualizarEnvio() {
        when(serviceEnvios.findById(1L)).thenReturn(envio);
        when(serviceEnvios.save(any(Envios.class))).thenReturn(envio);

        Envios datosActualizados = new Envios();
        datosActualizados.setFechaEnvio(LocalDate.now());
        datosActualizados.setNombrePedido("Pedido actualizado");
        datosActualizados.setSeguimiento("TRK999999");
        datosActualizados.setDireccion("Nueva dirección");
        datosActualizados.setEstado("enviado");

        ResponseEntity<Envios> response = enviosController.actualizar(1L, datosActualizados);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pedido actualizado", response.getBody().getNombrePedido());
        verify(serviceEnvios).save(any(Envios.class));
    }
}

