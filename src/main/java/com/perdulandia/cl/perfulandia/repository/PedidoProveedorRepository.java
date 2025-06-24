package com.perdulandia.cl.perfulandia.repository;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoProveedorRepository extends JpaRepository<Pedidoproveedor, Long> {

    List<Pedidoproveedor> findByFechaPedido(LocalDate fechaPedido);

    Pedidoproveedor findById(long id);

    Pedidoproveedor save(Pedidoproveedor pedidoproveedor);


}
