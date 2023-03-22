package com.driagon.springboot.datajpa.app.models.dao;

import com.driagon.springboot.datajpa.app.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    @Query("select p from Producto p where p.nombre LIKE %?1%")
    public List<Producto> findAllByNombre(String term);

    public List<Producto> findByNombreLikeIgnoreCase(String term);
}