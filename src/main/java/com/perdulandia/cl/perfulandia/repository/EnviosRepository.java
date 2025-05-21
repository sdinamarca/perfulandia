package com.perdulandia.cl.perfulandia.repository;

import com.perdulandia.cl.perfulandia.model.Envios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnviosRepository extends JpaRepository<Envios, Long> {
}

