/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.service;
import com.prueba.m5a.Leccion.model.IngresosEgresos;
import com.prueba.m5a.Leccion.repository.IngresosEgresosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bryan
 */
@Service
public class IngresosEgresosServiceImpl extends GenericServiceImpl<IngresosEgresos, Integer> implements GenericService<IngresosEgresos, Integer> {

    @Autowired
   IngresosEgresosRepository repository;

    @Override
    public CrudRepository<IngresosEgresos, Integer> getDao() {
        return repository;
    }

}
