package com.driagon.springboot.clientes.app.repositories;

import com.driagon.springboot.clientes.app.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}