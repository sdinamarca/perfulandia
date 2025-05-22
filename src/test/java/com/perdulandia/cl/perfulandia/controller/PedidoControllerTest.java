package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.service.ServicePedidoProveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoControllerTest{

    @Mock
    private ServicePedidoProveedor servicePedidoProveedor;

    @InjectMocks
    private PedidoController pedidoController;

    private Pedidoproveedor pedido;
    private Proveedor proveedor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        pedido = new Pedidoproveedor();
        pedido.setId(1L);
        pedido.setNumPedido("P-001");
        pedido.setFechaPedido(new Date());
        pedido.setProveedor(proveedor);
    }

    @Test
    public void testListarPedidosProveedor() {
        List<Pedidoproveedor> lista = Collections.singletonList(pedido);

        when(servicePedidoProveedor.findAll()).thenReturn(lista);

        ResponseEntity<List<Pedidoproveedor>> respuesta = pedidoController.listar();

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(servicePedidoProveedor, times(1)).findAll();
    }

    @Test
    public void testListarPedidosProveedorVacio() {
        when(servicePedidoProveedor.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pedidoproveedor>> respuesta = pedidoController.listar();

        assertEquals(204, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }

    @Test
    public void testBuscarPedidoPorIdExistente() {
        when(servicePedidoProveedor.findById(1L)).thenReturn(pedido);

        ResponseEntity<Pedidoproveedor> respuesta = pedidoController.buscar(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("P-001", respuesta.getBody().getNumPedido());
        verify(servicePedidoProveedor, times(1)).findById(1L);
    }

    @Test
    public void testBuscarPedidoPorIdNoExistente() {
        when(servicePedidoProveedor.findById(999L)).thenThrow(new NoSuchElementException());

        ResponseEntity<Pedidoproveedor> respuesta = pedidoController.buscar(999L);

        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testActualizarPedidoExistente() {
        Pedidoproveedor actualizado = new Pedidoproveedor();
        actualizado.setNumPedido("P-002");
        actualizado.setFechaPedido(new Date());
        actualizado.setProveedor(proveedor);

        when(servicePedidoProveedor.findById(1L)).thenReturn(pedido);
        when(servicePedidoProveedor.save(any(Pedidoproveedor.class))).thenReturn(actualizado);

        ResponseEntity<Pedidoproveedor> respuesta = pedidoController.actualizar(1L, actualizado);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("P-002", respuesta.getBody().getNumPedido());
    }

    @Test
    public void testActualizarPedidoNoExistente() {
        when(servicePedidoProveedor.findById(99L)).thenThrow(new NoSuchElementException());

        ResponseEntity<Pedidoproveedor> respuesta = pedidoController.actualizar(99L, new Pedidoproveedor());

        assertEquals(404, respuesta.getStatusCodeValue());
    }
}

