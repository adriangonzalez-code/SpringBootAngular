package com.driagon.springboot.datajpa.app.controllers;

import com.driagon.springboot.datajpa.app.models.dao.IClienteDao;
import com.driagon.springboot.datajpa.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteDaoJpa")
    private IClienteDao clienteDao;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", this.clienteDao.findAll());

        return "listar";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("titulo", "Formulario de Clientes");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Clientes");
            return "form";
        }

        this.clienteDao.save(cliente);
        status.setComplete();
        return "redirect:/listar";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable Long id, Map<String, Object> model) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = this.clienteDao.findOne(id);
        } else {
            return "redirect:/listar";
        }

        model.put("titulo", "Editar Cliente");
        model.put("cliente", cliente);

        return "form";
    }
}