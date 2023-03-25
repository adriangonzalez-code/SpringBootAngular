package com.driagon.springboot.clientes.app.services;

import com.driagon.springboot.clientes.app.entities.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    public Cliente save(Cliente cliente);

    public void delete(Long id);
}