package com.driagon.springboot.errores.app.services;

import com.driagon.springboot.errores.app.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private List<Usuario> lista;

    public UsuarioServiceImpl() {
        lista = new ArrayList<>();
        lista.add(new Usuario(1, "Adrian", "Gonzalez"));
        lista.add(new Usuario(2, "Andres", "Guzman"));
        lista.add(new Usuario(3, "Juan", "Perez"));
        lista.add(new Usuario(4, "John", "Doe"));
        lista.add(new Usuario(5, "Pepa", "Garcia"));
        lista.add(new Usuario(6, "Luci", "Fernández"));
        lista.add(new Usuario(7, "Paco", "Rodriguez"));
    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {

        Usuario resultado = null;

        for(Usuario u : this.lista) {
            if (u.getId().equals(id)) {
                resultado = u;
                break;
            }
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> obtenerPorIdOptional(Integer id) {
        Usuario usuario = this.obtenerPorId(id);
        return Optional.ofNullable(usuario);
    }
}
