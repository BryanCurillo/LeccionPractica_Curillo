/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Producto;
import com.prueba.m5a.Leccion.service.ProductoServiceImpl;
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
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoServiceImpl serviceImpl;

    @Operation(summary = "Se obtiene lista de Categorias")
    @GetMapping("/get")
    public ResponseEntity<List<Producto>> lista() {
        return new ResponseEntity<>(serviceImpl.findByAll(), HttpStatus.OK);
    }

//    @GetMapping("/get/{id}")
    public Producto ProductoXid(@PathVariable Integer id) {

        return serviceImpl.findById(id);
    }

    @Operation(summary = "Debe enviar los campos del Rol")
    @PostMapping("/post")
    public ResponseEntity<Producto> crear(@RequestBody Producto p) {
        return new ResponseEntity<>(serviceImpl.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto p) {
        Producto prod = serviceImpl.findById(id);
        if (prod != null) {
            try {
                prod.setCantidad(p.getCantidad());
                prod.setDescripcion(p.getDescripcion());
                prod.setImagen(p.getImagen());
                prod.setNombre(p.getNombre());
                prod.setPrecio_PVP(p.getPrecio_PVP());
                prod.setPrecio_emp(p.getPrecio_emp());

                return new ResponseEntity<>(serviceImpl.save(prod), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/putPVP/{id}")
    public Boolean actualizarPVP(Integer id, Double porcentaje) {
        System.out.println("imp= " + serviceImpl);

        Producto prod = serviceImpl.findById(id);
        if (prod != null) {
            try {
                prod.setPrecio_PVP(prod.getPrecio_emp() + prod.getPrecio_emp() * (porcentaje / 100));

                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean actualizarInv(Integer id, Integer Cantidad) {
        Producto prod = serviceImpl.findById(id);
        if (prod != null) {
            try {
                if (prod.getCantidad() - Cantidad >= 0) {
                    prod.setCantidad(prod.getCantidad() - Cantidad);
                    serviceImpl.save(prod);
                    return true;
                } else {
                    System.out.println("FONDOS INSUFICIENTES");
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Producto> eliminar(@PathVariable Integer id) {
        serviceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
