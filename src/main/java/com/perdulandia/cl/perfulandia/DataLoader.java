package com.perdulandia.cl.perfulandia;

import com.perdulandia.cl.perfulandia.model.*;
import com.perdulandia.cl.perfulandia.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EnviosRepository enviosRepository;

    @Autowired
    private PedidoProveedorRepository pedidoProveedorRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker():
        Random random = new Random();


    }
}
