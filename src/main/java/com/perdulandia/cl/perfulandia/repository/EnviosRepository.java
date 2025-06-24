package com.perdulandia.cl.perfulandia.repository;

import com.perdulandia.cl.perfulandia.model.Envios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Date;

@Repository
public interface EnviosRepository extends JpaRepository<Envios, Long>  {


    List<Envios> findByFechaEnvio(LocalDate fechaEnvio);

}

