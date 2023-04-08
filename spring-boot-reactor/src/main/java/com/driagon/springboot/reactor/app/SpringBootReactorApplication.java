package com.driagon.springboot.reactor.app;

import com.driagon.springboot.reactor.app.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringBootReactorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Flux<String> nombres = Flux.just("Adrian Gonzalez", "Andres Guzman", "Pedro Fulando", "Maria Funala", "Diego Sultano", "Juan Perez", "Bruce Lee", "Bruce Willis");
        List<String> usuariosList = new ArrayList<>();
        usuariosList.add("Adrian Gonzalez");
        usuariosList.add("Andres Guzman");
        usuariosList.add("Juan Perez");
        usuariosList.add("Diego Fulano");
        usuariosList.add("Bruce Lee");
        usuariosList.add("Bruce Willis");
        usuariosList.add("Bruce Wayne");

        Flux<String> nombres = Flux.fromIterable(usuariosList);

        Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase("bruce"))
                .doOnNext(usuario -> {
                    if (usuario == null) {
                        throw new RuntimeException("Nombres no pueden ser vacíos");
                    }

                    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
                }).map(usuario -> {
                    String nombre = usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);
                    return usuario;
                });

        usuarios.subscribe(usuario -> log.info(usuario.toString()), error -> log.error(error.getMessage()), new Runnable() {
            @Override
            public void run() {
                log.info("Haz finalizado la ejecución");
            }
        });
    }
}