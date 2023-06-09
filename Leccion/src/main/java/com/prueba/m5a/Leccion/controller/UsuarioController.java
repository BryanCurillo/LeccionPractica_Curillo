/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Usuario;
import com.prueba.m5a.Leccion.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
//@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Operation(summary = "Se obtiene la lista de Usuarios")
    @GetMapping("/get")
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
    }

    public Boolean validarProveedor(Integer id) {
        boolean ban = false;
        for (Usuario u : usuarioService.findByAll()) {
            if (u.getRol().getId_rol() == 4 && u.getId_usuario() == id) {
                ban = true;
                break;
            } else {
                ban = false;
            }
        }
        return ban;
    }

    public Boolean validarCliente(Integer id) {
        boolean ban = false;
        for (Usuario u : usuarioService.findByAll()) {
            if (u.getRol().getId_rol() == 3 && u.getId_usuario() == id) {
                ban = true;
                break;
            } else {
                ban = false;
            }
        }
        return ban;
    }
//           

    @Operation(summary = "Debe enviar los campos del Usuario")
    @PostMapping("/post")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario u) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setClave(passwordEncoder.encode(u.getClave()));
        return new ResponseEntity<>(usuarioService.save(u), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                usuario.setClave(passwordEncoder.encode(u.getClave()));
                usuario.setCorreo(u.getCorreo());
                usuario.setPersona(u.getPersona());
                usuario.setRol(u.getRol());
                return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Usuario> elimiarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
