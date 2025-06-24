package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.assemblers.PedidosproveedorModelAssembler;
import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.service.ServicePedidoProveedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pedidosproveedor")
public class PedidoControllerV2 {


    @Autowired
    private PedidosproveedorModelAssembler assembler;
    @Autowired
    private ServicePedidoProveedor servicePedidoProveedor;

    //LISTAR
    @GetMapping
    @Operation(summary = "Listar todos los pedidos", description = "Muestra todos los pedidos a proveedores existentes")
    public ResponseEntity<List<Pedidoproveedor>> listaTodo(){
        List<Pedidoproveedor> pedidos= servicePedidoProveedor.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);

    }

    //LISTAR + assembler + HATEOAS
    @GetMapping(value="/hateoas",produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pedidoproveedor>> getAllPedidosHATEOAS() {
        List<Pedidoproveedor> lista = servicePedidoProveedor.findAll(); // asegúrate que esto es una List<Carrera>

        List<EntityModel<Pedidoproveedor>> pedidos = lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidosHATEOAS()).withSelfRel());
    }
    //LISTAR independiente + HATEOAS
    @GetMapping(value="/hateoasV2",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Pedidoproveedor>>> getAllPedidosHATEOASv2() {
        List<Pedidoproveedor> lista = servicePedidoProveedor.findAll(); // asegúrate que esto es una List<Carrera>
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Pedidoproveedor>> pedidos = lista.stream()
                .map(pedido -> EntityModel.of(pedido,
                        linkTo(methodOn(PedidoControllerV2.class)
                                .getByIdPedidoHATEOAS(pedido.getId()))
                                .withSelfRel()))
                .toList();

        CollectionModel<EntityModel<Pedidoproveedor>> collectionModel = CollectionModel.of(
                pedidos,
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidosHATEOASv2()).withSelfRel()
        );
        return ResponseEntity.ok(collectionModel);
    }
    // BUSCAR
    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pedido por id", description = "Busca un pedido existente")
    public ResponseEntity<Pedidoproveedor> buscar(@PathVariable Long id){
        try {
            Pedidoproveedor pedido = servicePedidoProveedor.findById(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // BUSCAR + HATEOAS
    @GetMapping(value="/hateoas/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar un pedido por id + HATEOAS", description = "Busca un pedido existente")
    public ResponseEntity<EntityModel<Pedidoproveedor>> getByIdPedidoHATEOAS(@PathVariable Long id) {
        try {
            Pedidoproveedor pedido = servicePedidoProveedor.findById(id);
            if (pedido == null) {return ResponseEntity.notFound().build();}

            EntityModel<Pedidoproveedor> pedidoproveedorEntityModel= EntityModel.of(pedido,
                    linkTo(methodOn(PedidoControllerV2.class)
                            .getByIdPedidoHATEOAS(pedido.getId())).withSelfRel());

            return ResponseEntity.ok(pedidoproveedorEntityModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    // AGREGAR
    @PostMapping
    @Operation(summary = "Agregar un pedido", description = "Ingresa un pedido no existente")
    public ResponseEntity<Pedidoproveedor> guardar(@RequestBody Pedidoproveedor pedidoproveedor){
        Pedidoproveedor pedidoNuevo = servicePedidoProveedor.save(pedidoproveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoNuevo);
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido", description = "Actualiza un pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizada exitosamente",
                    content = @Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Pedidoproveedor.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedidoproveedor> actualizar(@PathVariable Long id, @RequestBody Pedidoproveedor pedidoproveedor){
        try {
            Pedidoproveedor newPedidoProveedor = servicePedidoProveedor.findById(id);
            newPedidoProveedor.setId(id);
            newPedidoProveedor.setNumPedido(pedidoproveedor.getNumPedido());

            servicePedidoProveedor.save(newPedidoProveedor);
            return ResponseEntity.ok(pedidoproveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

