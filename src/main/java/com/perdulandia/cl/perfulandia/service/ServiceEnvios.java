package com.perdulandia.cl.perfulandia.service;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.repository.EnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceEnvios {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envios> findAll(){

        return envioRepository.findAll();
    }

    public Envios findById(long id){

        return envioRepository.findById(idPedido).get();
    }

    public Envios save(Envios envios) {

        return envioRepository.save(envios);
    }

    public void delete(Long id) {

        envioRepository.deleteById(idPedidos);
    }
}
