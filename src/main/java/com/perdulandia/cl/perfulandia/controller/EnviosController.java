package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.service.ServiceEnvios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import com.perdulandia.cl.perfulandia.repository.EnviosRepository;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/envios")
public class EnviosController{

    @Autowired
    private ServiceEnvios serviceEnvios;
    @Autowired
    private EnviosRepository enviosRepository;


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
    public ResponseEntity<Envios> actualizar(@PathVariable Long id, @RequestBody Envios envios) {
        try {
            Envios existente = serviceEnvios.findById(id);
            existente.setFechaEnvio(envios.getFechaEnvio());
            existente.setNombrePedido(envios.getNombrePedido());
            existente.setSeguimiento(envios.getSeguimiento());
            existente.setDireccion(envios.getDireccion());

            serviceEnvios.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/envios/fecha/{fechaEnvio}")
    public ResponseEntity<List<Envios>> buscarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEnvio) {
        try {
            List<Envios> envios = enviosRepository.findByFechaEnvio(fechaEnvio);
            if (envios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(envios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            Envios existente = serviceEnvios.findById(id);
            if (existente != null) {
                serviceEnvios.delete(id);
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }




}


