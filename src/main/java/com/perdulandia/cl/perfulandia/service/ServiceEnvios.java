package com.perdulandia.cl.perfulandia.service;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.repository.EnviosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceEnvios {



    @Autowired
    private EnviosRepository enviosRepository;

    public List<Envios> findAll(){
        return enviosRepository.findAll();
    }

    public Envios findById(long idPedido){
        return enviosRepository.findById(idPedido).get();
    }

    public Envios save(Envios envios) {
        return enviosRepository.save(envios);
    }

    public void delete(Long idPedidos) {
        enviosRepository.deleteById(idPedidos);
    }
}
