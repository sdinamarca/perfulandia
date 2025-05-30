package com.perdulandia.cl.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false, unique = true)
    private Long idPedido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "nombre_pedido", nullable = false, length = 50)
    private String nombrePedido;

    @Column(name = "seguimiento", length = 100)
    private String seguimiento;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "estado", length = 30)
    private String estado;//(procesando, enviado, entregado)

}
