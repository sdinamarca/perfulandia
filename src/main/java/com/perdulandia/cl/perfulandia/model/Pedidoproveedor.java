package com.perdulandia.cl.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "pedido_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedidoproveedor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "num_pedido", nullable = false, unique = true)
    private String numPedido;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @ManyToOne
    @JoinColumn(name = "idProvedor", nullable = false)
    private Proveedor proveedor;

}
