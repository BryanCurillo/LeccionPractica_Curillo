/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.m5a.Leccion.repository;

import com.prueba.m5a.Leccion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Bryan
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "Select * from usuario u where u.nombre = :nombre", nativeQuery = true)
    public Usuario buscarUsuario(String nombre);
}
