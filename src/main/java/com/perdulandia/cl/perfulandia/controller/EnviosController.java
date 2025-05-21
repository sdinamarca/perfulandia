package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.service.ServiceEnvios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnviosController {

    @Autowired
    private ServiceEnvios serviceEnvios;

    @GetMapping
    public ResponseEntity<List<Envios>> listar() {
        List<Envios> envios = serviceEnvios.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envios> guardar(@RequestBody Envios envios) {
        Envios nuevoEnvios = serviceEnvios.save(envios);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envios> buscar(@PathVariable Long id) {
        try {
            Envios envios = serviceEnvios.findById(id);
            return ResponseEntity.ok(envios);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envios> actualizar(@PathVariable Long id, @RequestBody Envios envio) {
        try {
            Envios existente = serviceEnvios.findById(id);
            existente.setFechaEnvio(envio.getFechaEnvio());
            existente.setNombrePedido(envio.getNombrePedido());
            existente.setSeguimiento(envio.getSeguimiento());
            existente.setDireccion(envio.getDireccion());

            serviceEnvios.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            serviceEnvios.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}


