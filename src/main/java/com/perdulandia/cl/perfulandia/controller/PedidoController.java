package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.service.ServicePedidoProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos-proveedor")
public class PedidoController {

    @Autowired
    private ServicePedidoProveedor servicePedidoProveedor;

    // GET: Listar todos los pedidos de proveedor
    @GetMapping
    public ResponseEntity<List<Pedidoproveedor>> listar() {
        List<Pedidoproveedor> lista = servicePedidoProveedor.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedidoproveedor> buscar(@PathVariable Long id) {
        try {
            Pedidoproveedor pedido = servicePedidoProveedor.findById(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Actualizar un pedido proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Pedidoproveedor> actualizar(@PathVariable Long id, @RequestBody Pedidoproveedor datos) {
        try {
            Pedidoproveedor existente = servicePedidoProveedor.findById(id);

            existente.setNumPedido(datos.getNumPedido());
            existente.setFechaPedido(datos.getFechaPedido());
            existente.setProveedor(datos.getProveedor());

            servicePedidoProveedor.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

