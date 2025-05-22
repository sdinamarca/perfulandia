package com.perdulandia.cl.perfulandia.controller;

import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() {
        List<Proveedor> lista = proveedorRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // Buscar proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscar(@PathVariable Long id) {
        return proveedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear proveedor
    @PostMapping
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    // Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor datos) {
        return proveedorRepository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setEmail(datos.getEmail());
            existente.setTelefono(datos.getTelefono());
            return ResponseEntity.ok(proveedorRepository.save(existente));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
