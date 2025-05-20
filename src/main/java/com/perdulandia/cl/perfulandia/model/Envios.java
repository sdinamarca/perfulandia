package com.perdulandia.cl.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envios {


    @Column(name = "num_pedido", nullable = false, unique = true)
    private String numPedido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Column(name = "nombre_pedido", nullable = false, length = 100)
    private String nombrePedido;

    @Column(name = "seguimiento", length = 100)
    private String seguimiento;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

}
