package com.perdulandia.cl.perfulandia.assemblers;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;

import com.perdulandia.cl.perfulandia.repository.PedidoProveedorRepository;
import com.perdulandia.cl.perfulandia.controller.PedidoControllerV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidosproveedorModelAssembler implements RepresentationModelAssembler<Pedidoproveedor, EntityModel<Pedidoproveedor>> {


    @Autowired
    private PedidoProveedorRepository pedidoRepository;

    @Override
    public EntityModel<Pedidoproveedor> toModel(Pedidoproveedor pedidoproveedor) {
        return EntityModel.of(pedidoproveedor,
                linkTo(methodOn(PedidoControllerV2.class).getByIdPedidoHATEOAS(pedidoproveedor.getId())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidosHATEOAS()).withRel("pedidos"));
    }

}
