/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Rol;
import com.prueba.m5a.Leccion.service.RolServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolServiceImpl rolService;

    @Operation(summary = "Se obtiene lista de Roles")
    @GetMapping("/get")
    public ResponseEntity<List<Rol>> listaRoles() {
        return new ResponseEntity<>(rolService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Rol> RolXid(@PathVariable Integer id) {
        
        return new ResponseEntity<>(rolService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Debe enviar los campos del Rol")
    @PostMapping("/post")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol r) {
        return new ResponseEntity<>(rolService.save(r), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol r) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            try {
                rol.setNombre(r.getNombre());
                return new ResponseEntity<>(rolService.save(rol), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable Integer id) {
        rolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
