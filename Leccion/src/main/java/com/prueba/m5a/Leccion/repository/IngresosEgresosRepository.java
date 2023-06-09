/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.m5a.Leccion.repository;

import com.prueba.m5a.Leccion.model.IngresosEgresos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Bryan
 */
public interface IngresosEgresosRepository extends JpaRepository<IngresosEgresos, Integer> {

    @Query(value = "Select * from persona p where p.nombre = :nombre", nativeQuery = true)
    public IngresosEgresos buscarRol(String nombre);
}
