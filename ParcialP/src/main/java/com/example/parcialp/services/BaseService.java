package com.example.parcialp.services;

import com.example.parcialp.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception; //lista de personas que se encuentran en la base de datos

    public Page<E> findAll(Pageable pageable) throws Exception;

    public E findById(ID id) throws Exception; //trae una persona de acuerdo a su numero de id

    public E save(E entity) throws Exception; //crea nueva entidad

    public E update(ID id, E entity) throws Exception; // con el id actualiza la entidad

    public boolean delete(ID id) throws Exception; //elimina
}

