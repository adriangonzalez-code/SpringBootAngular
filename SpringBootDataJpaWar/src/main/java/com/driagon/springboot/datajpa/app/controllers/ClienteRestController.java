package com.driagon.springboot.datajpa.app.controllers;

import com.driagon.springboot.datajpa.app.models.service.IClienteService;
import com.driagon.springboot.datajpa.app.view.xml.ClienteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping(value = "/listar")
    public ClienteList listar() {
        return new ClienteList(this.clienteService.findAll());
    }
}