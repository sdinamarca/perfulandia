package com.perdulandia.cl.perfulandia;

import com.perdulandia.cl.perfulandia.model.Envios;
import com.perdulandia.cl.perfulandia.model.Pedidoproveedor;
import com.perdulandia.cl.perfulandia.model.Proveedor;
import com.perdulandia.cl.perfulandia.repository.EnviosRepository;
import com.perdulandia.cl.perfulandia.repository.PedidoProveedorRepository;
import com.perdulandia.cl.perfulandia.repository.ProveedorRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        Faker faker = new Faker();
        Random random = new Random();

        // Crear Proveedores
        for (int i = 0; i < 10; i++) {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(faker.company().name());
            proveedor.setEmail(faker.internet().emailAddress());
            proveedor.setTelefono(faker.phoneNumber().cellPhone());
            proveedorRepository.save(proveedor);
        }

        List<Proveedor> proveedores = proveedorRepository.findAll();

        // Crear Pedidos de Proveedor
        for (int i = 0; i < 20; i++) {
            Pedidoproveedor pedido = new Pedidoproveedor();
            pedido.setNumPedido("ORD-" + faker.number().digits(6));
            pedido.setFechaPedido(LocalDate.now().minusDays(random.nextInt(30)));
            pedido.setProveedor(proveedores.get(random.nextInt(proveedores.size())));
            pedidoProveedorRepository.save(pedido);
        }

        List<Pedidoproveedor> pedidos = pedidoProveedorRepository.findAll();

        // Crear Envios
        String[] estados = {"procesando", "enviado", "entregado"};

        for (int i = 0; i < 20; i++) {
            Envios envio = new Envios();
            envio.setFechaEnvio(LocalDate.now().minusDays(random.nextInt(15)));
            envio.setNombrePedido("Pedido " + faker.commerce().productName());
            envio.setSeguimiento(faker.code().imei());
            envio.setDireccion(faker.address().fullAddress());
            envio.setEstado(estados[random.nextInt(estados.length)]);
            enviosRepository.save(envio);
        }

        System.out.println("✅ Datos de prueba generados correctamente para Perfulandia.");
    }
}
