package com.driagon.springboot.webflux.app.controllers;

import com.driagon.springboot.webflux.app.documents.Producto;
import com.driagon.springboot.webflux.app.repositories.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);

    @Autowired
    private ProductoRepository repository;

    @GetMapping()
    public ResponseEntity<Flux<Producto>> index() {
        Flux<Producto> productos = this.repository.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).doOnNext(producto -> log.info(producto.getNombre()));

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public Mono<Producto> show(@PathVariable String id) {
        //Mono<Producto> productos = this.repository.findById(id);

        Flux<Producto> productos = this.repository.findAll();

        Mono<Producto> producto = productos.filter(p -> p.getId().equals(id)).next().doOnNext(p -> log.info(p.getNombre()));

        return producto;
    }
}