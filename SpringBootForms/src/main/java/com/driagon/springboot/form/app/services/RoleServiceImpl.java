package com.driagon.springboot.form.app.services;

import com.driagon.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    List<Role> roles;

    public RoleServiceImpl() {
        roles = new ArrayList<>();
        roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        roles.add(new Role(2, "Usuario", "ROLE_USER"));
        roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
    }

    @Override
    public List<Role> listar() {
        return this.roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {

        Role resultado = null;

        for(Role role : this.roles) {
            if (id == role.getId()) {
                resultado = role;
                break;
            }
        }

        return resultado;
    }
}
