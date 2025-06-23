package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.service.ServicePedidoProveedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perdulandia.cl.perfulandia.repository.PedidoProveedorRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos-proveedor")
@Tag(name = "Pedidos a Proveedor", description = "Operaciones para gestionar pedidos a proveedores")
public class PedidoController{

    @Autowired
    private ServicePedidoProveedor servicePedidoProveedor;

    @Autowired
    private PedidoProveedorRepository pedidoProveedorRepository;


    // GET: Listar todos los pedidos de proveedor
    @GetMapping
    @Operation(summary = "Listar todos los pedidos de proveedor")
    public ResponseEntity<List<Pedidoproveedor>> listar() {
        List<Pedidoproveedor> lista = servicePedidoProveedor.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pedido por ID")
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
    @Operation(
            summary = "Actualizar un pedido proveedor",
            description = "Actualiza un pedido proveedor existente por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido actualizado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedidoproveedor.class)
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido no encontrado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida"
            )
    })
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





    @GetMapping("/pedido/fecha/{fechaPedido}")
    @Operation(summary = "Buscar pedidos por fecha de pedido")
    public ResponseEntity<List<Pedidoproveedor>> buscarPorFechaPedido(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPedido) {
        try {
            List<Pedidoproveedor> pedidosProveedor = pedidoProveedorRepository.findByFechaPedido(fechaPedido);
            if (pedidosProveedor.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pedidosProveedor);
        } catch (Exception e) {
            // Aquí puedes agregar un logger para registrar el error
            // logger.error("Error buscando pedidos por fecha: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

