package com.driagon.springboot.app.controllers;

import com.driagon.springboot.app.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")
public class IndexController {

    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;

    @Value("${texto.indexcontroller.perfil.titulo}")
    private String textoPerfil;

    @Value("${texto.indexcontroller.listar.titulo}")
    private String textoListar;

    // @RequestMapping(value = "/index", method = RequestMethod.GET)
    @GetMapping({"/index", "/", "/home", ""})
    public String index(Model model) {
        model.addAttribute("titulo", textoIndex);
        return "index";
    }

    @RequestMapping("/perfil")
    public String perfil(Model model) {

        Usuario usuario = new Usuario();
        usuario.setNombre("Adrian");
        usuario.setApellido("Gonzalez");
        usuario.setEmail("adrian@correo.com");

        model.addAttribute("titulo", textoPerfil + usuario.getNombre());
        model.addAttribute("usuario", usuario);

        return "perfil";
    }

    @RequestMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("titulo", textoListar);

        return "listar";
    }

    @ModelAttribute("usuarios")
    public List<Usuario> poblarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario("Adrian", "Gonzalez", "adrian@correo.com"),
                new Usuario("Andres", "Guzman", "andres@correo.com"),
                new Usuario("Juan", "Perez", "juan@correo.com"),
                new Usuario("John", "Doe", "john@correo.com"),
                new Usuario("Tornado", "Roe", "tornado@correo.com"));

        return usuarios;
    }
}