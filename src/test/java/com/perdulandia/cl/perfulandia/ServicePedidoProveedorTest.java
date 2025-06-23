package com.perdulandia.cl.perfulandia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.repository.PedidoProveedorRepository;
import com.perdulandia.cl.perfulandia.service.ServicePedidoProveedor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServicePedidoProveedorTest {

    @Autowired
    private ServicePedidoProveedor service;

    @MockBean
    private PedidoProveedorRepository pPRepository;

    // Simula un proveedor para los tests
    private Proveedor proveedorEjemplo = new Proveedor(1L, "Proveedor S.A.", "contacto@proveedor.cl", "123456789");

    @Test
    public void testFindAll() {
        Pedidoproveedor pedido = new Pedidoproveedor(1L, "PED-001", LocalDate.of(2023, 6, 21), proveedorEjemplo
        );

        when(pPRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedidoproveedor> resultado = service.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testFindById() {
        Pedidoproveedor pedido = new Pedidoproveedor(
                2L,
                "PED-002",
                LocalDate.now(),
                proveedorEjemplo
        );

        when(pPRepository.findById(2L)).thenReturn(Optional.of(pedido));

        Pedidoproveedor resultado = service.findById(2L);

        assertNotNull(resultado);
    }

    @Test
    public void testSave() {
        Pedidoproveedor pedido = new Pedidoproveedor(
                3L,
                "PED-003",
                LocalDate.now(),
                proveedorEjemplo
        );

        when(pPRepository.save(pedido)).thenReturn(pedido);

        Pedidoproveedor resultado = service.save(pedido);

        assertNotNull(resultado);
    }
}
