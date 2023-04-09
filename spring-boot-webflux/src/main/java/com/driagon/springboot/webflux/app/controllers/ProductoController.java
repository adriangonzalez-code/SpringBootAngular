package com.driagon.springboot.webflux.app.controllers;

import com.driagon.springboot.webflux.app.documents.Producto;
import com.driagon.springboot.webflux.app.repositories.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoRepository repository;

    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        Flux<Producto> productos = this.repository.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        });

        productos.subscribe(producto -> log.info(producto.getNombre()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de Productos");

        return "listar";
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<Producto> productos = this.repository.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).delayElements(Duration.ofSeconds(1));

        productos.subscribe(producto -> log.info(producto.getNombre()));

        model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 2));
        model.addAttribute("titulo", "Listado de Productos");

        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<Producto> productos = this.repository.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).repeat(5000);

        productos.subscribe(producto -> log.info(producto.getNombre()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de Productos");

        return "listar";
    }

    @GetMapping("/listar-chuncked")
    public String listarChuncked(Model model) {
        Flux<Producto> productos = this.repository.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).repeat(5000);

        productos.subscribe(producto -> log.info(producto.getNombre()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de Productos");

        return "listar-chunked";
    }
}