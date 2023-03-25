package com.driagon.springboot.clientes.app.services;

import com.driagon.springboot.clientes.app.entities.Cliente;
import com.driagon.springboot.clientes.app.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) this.clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.clienteRepository.deleteById(id);
    }
}