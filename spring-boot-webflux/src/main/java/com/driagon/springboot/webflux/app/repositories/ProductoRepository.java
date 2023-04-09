package com.driagon.springboot.webflux.app.repositories;

import com.driagon.springboot.webflux.app.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}