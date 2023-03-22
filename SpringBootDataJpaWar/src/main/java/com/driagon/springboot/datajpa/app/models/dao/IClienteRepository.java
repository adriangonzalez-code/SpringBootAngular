package com.driagon.springboot.datajpa.app.models.dao;

import com.driagon.springboot.datajpa.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

    @Query("select c from Cliente c left join fetch c.facturas f where c.id = ?1")
    public Cliente fetchByIdWithFacturas(Long id);
}