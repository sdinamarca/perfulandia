package com.perdulandia.cl.perfulandia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.repository.ProveedorRepository;
import com.perdulandia.cl.perfulandia.service.ServiceProveedor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ServiceProveedorTest {

    @Autowired
    private ServiceProveedor serviceProveedor;

    @MockBean
    private ProveedorRepository proveedorRepository;

    @Test
    public void testFindAll() {
        Proveedor proveedor = new Proveedor(1L, "Proveedor Uno", "correo@proveedor.cl", "123456789");

        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Proveedor> resultado = serviceProveedor.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testFindById() {
        Proveedor proveedor = new Proveedor(2L, "Proveedor Dos", "otro@proveedor.cl", "987654321");

        when(proveedorRepository.findById(2L)).thenReturn(Optional.of(proveedor));

        Proveedor resultado = serviceProveedor.findById(2L);

        assertNotNull(resultado);
    }

    @Test
    public void testSave() {
        Proveedor proveedor = new Proveedor(3L, "Proveedor Guardado", "guardar@proveedor.cl", "111222333");

        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor resultado = serviceProveedor.save(proveedor);

        assertNotNull(resultado);
    }

    @Test
    public void testDelete() {
        Long id = 4L;

        serviceProveedor.delete(id);

        verify(proveedorRepository, times(1)).deleteById(id);
    }
}
