/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.service;


import com.prueba.m5a.Leccion.model.Venta;
import com.prueba.m5a.Leccion.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bryan
 */
@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Integer> implements GenericService<Venta, Integer> {

    @Autowired
    VentaRepository repository;

    @Override
    public CrudRepository<Venta, Integer> getDao() {
        return repository;
    }
}
