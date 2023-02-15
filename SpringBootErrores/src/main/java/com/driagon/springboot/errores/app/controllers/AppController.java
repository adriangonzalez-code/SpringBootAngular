package com.driagon.springboot.errores.app.controllers;

import com.driagon.springboot.errores.app.errors.UsuarioNoEncontradoException;
import com.driagon.springboot.errores.app.models.domain.Usuario;
import com.driagon.springboot.errores.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String index() {
        Integer valor = 100 / 0;
        //Integer valor = Integer.parseInt("10xaaa");
        return "index";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        //Usuario usuario = this.usuarioService.obtenerPorId(id);
        /*if (usuario == null) {
            throw new UsuarioNoEncontradoException(id.toString());
        }*/

        Usuario usuario = this.usuarioService.obtenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString()));

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));
        return "ver";
    }
}