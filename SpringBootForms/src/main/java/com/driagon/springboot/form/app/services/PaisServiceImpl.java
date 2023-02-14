package com.driagon.springboot.form.app.services;

import com.driagon.springboot.form.app.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {

    List<Pais> lista;

    public PaisServiceImpl() {
        lista = Arrays.asList(
                new Pais(1, "CR", "Costa Rica"),
                new Pais(2, "EU", "Estados Unidos de América"),
                new Pais(3, "ES", "España"),
                new Pais(4, "CL", "Chile"),
                new Pais(5, "MX", "México"),
                new Pais(6, "AR", "Argentina"),
                new Pais(7, "PE", "Perú"),
                new Pais(8, "CO", "Colombia"),
                new Pais(9, "VE", "Venezuela"));
    }

    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {

        Pais resultado = null;

        for (Pais pais : this.lista) {
            if (id == pais.getId()) {
                resultado = pais;
                break;
            }
        }
        
        return resultado;
    }
}