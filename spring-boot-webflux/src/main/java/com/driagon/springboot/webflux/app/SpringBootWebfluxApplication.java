package com.driagon.springboot.webflux.app;

import com.driagon.springboot.webflux.app.documents.Producto;
import com.driagon.springboot.webflux.app.repositories.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        this.mongoTemplate.dropCollection("productos").subscribe();

        Flux.just(
                new Producto("TV Panasonic Pantalla LCD", 456.123),
                new Producto("Sony Camara HD Digital", 177.89),
                new Producto("Apple iPod Nano", 46.89),
                new Producto("Sony Notebook", 846.87),
                new Producto("Hewlett Packard Multifuncional", 200.894),
                new Producto("Bianchi Bicicleta", 78.458),
                new Producto("HP Notebook Omen 17", 2500.81),
                new Producto("Mica Cómoda 5 Cajones", 1503.98),
                new Producto("TV Sony Bravia OLED 4K Ulta HD", 2255.89)
        )
                .flatMap(producto -> {
                    producto.setCreateAt(new Date());
                    return this.productoRepository.save(producto);
                })
                .subscribe(producto -> log.info("Insert: " + producto.getId() + " " + producto.getNombre()));
    }
}