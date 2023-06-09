/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Categoria;
import com.prueba.m5a.Leccion.service.CategoriaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bryan
 */
//@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaServiceImpl serviceImpl;

    @Operation(summary = "Se obtiene lista de Categorias")
    @GetMapping("/get")
    public ResponseEntity<List<Categoria>> listaCategorias() {
        return new ResponseEntity<>(serviceImpl.findByAll(), HttpStatus.OK);
    }

    @Operation(summary = "Debe enviar los campos del Rol")
    @PostMapping("/post")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria c) {
        return new ResponseEntity<>(serviceImpl.save(c), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria c) {
        Categoria cat = serviceImpl.findById(id);
        if (cat != null) {
            try {
                cat.setNombre(c.getNombre());
                return new ResponseEntity<>(serviceImpl.save(cat), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Integer id) {
        serviceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
