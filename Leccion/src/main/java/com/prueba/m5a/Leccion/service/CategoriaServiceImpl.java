/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.service;


import com.prueba.m5a.Leccion.model.Categoria;
import com.prueba.m5a.Leccion.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bryan
 */
@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Integer> implements GenericService<Categoria, Integer> {

    @Autowired
    CategoriaRepository  categoriaRepository;

    @Override
    public CrudRepository<Categoria, Integer> getDao() {
        return categoriaRepository;
    }
}
