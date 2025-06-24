package com.perdulandia.cl.perfulandia.service;

import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceProveedor{


    @Autowired
    private ProveedorRepository ProveedorRepository;

    public List<Proveedor> findAll(){
        return ProveedorRepository.findAll();
    }

    public Proveedor findById(long idProveedor){
        return ProveedorRepository.findById(idProveedor).get();
    }

    public Proveedor save(Proveedor proveedor) {
        return ProveedorRepository.save(proveedor);
    }

    public void delete(Long idProveedor) {ProveedorRepository.deleteById(idProveedor);}
}
