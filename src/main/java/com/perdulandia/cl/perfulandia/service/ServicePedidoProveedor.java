package com.perdulandia.cl.perfulandia.service;

import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.repository.PedidoProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServicePedidoProveedor{

    @Autowired
    private PedidoProveedorRepository pPRepository;

    public List<Pedidoproveedor> findAll(){
        return pPRepository.findAll();
    }

    public Pedidoproveedor findById(long id){
        return pPRepository.findById(id).get();
    }

    public Pedidoproveedor save(Pedidoproveedor pproveedor) {
        return pPRepository.save(pproveedor);
    }
}

