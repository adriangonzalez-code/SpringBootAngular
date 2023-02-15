package com.driagon.springboot.errores.app.errors;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String id) {
        super("Usuario con id: ".concat(id).concat(" no existe en el sistema"));
    }
}