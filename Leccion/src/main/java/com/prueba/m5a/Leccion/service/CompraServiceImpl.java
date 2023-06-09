/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.service;


import com.prueba.m5a.Leccion.model.Compra;
import com.prueba.m5a.Leccion.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bryan
 */
@Service
public class CompraServiceImpl extends GenericServiceImpl<Compra, Integer> implements GenericService<Compra, Integer> {

    @Autowired
    CompraRepository repository;

    @Override
    public CrudRepository<Compra, Integer> getDao() {
        return repository;
    }
}
