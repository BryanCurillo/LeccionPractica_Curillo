/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Compra;
import com.prueba.m5a.Leccion.model.Producto;
import com.prueba.m5a.Leccion.model.Usuario;
import com.prueba.m5a.Leccion.service.CompraServiceImpl;
import com.prueba.m5a.Leccion.service.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
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
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    CompraServiceImpl serviceImpl;

    @Autowired
    ProductoController productoController;

    @Autowired
    UsuarioController usuarioController;

//    @Autowired
//    IngresosEgresosController iec;

    @Operation(summary = "Se obtiene lista de Categorias")
    @GetMapping("/get")
    public ResponseEntity<List<Compra>> listaCompras() {
        return new ResponseEntity<>(serviceImpl.findByAll(), HttpStatus.OK);
    }

    public Double egresos() {
        double egresos = 0;
        for (Compra c : serviceImpl.findByAll()) {
            egresos = egresos + (c.getProducto().getCantidad() * c.getProducto().getPrecio_emp());

        }
        return egresos;
    }

//    @Operation(summary = "Debe enviar los campos del Rol")
    @PostMapping("/post")
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra c) {
        if (usuarioController.validarProveedor(c.getProveedor().getId_usuario())) {
            productoController.actualizarPVP(c.getProducto().getId_producto(), c.getGanancia());
//            iec.actualizar(1);

            return new ResponseEntity<>(serviceImpl.save(c), HttpStatus.CREATED);

        } else {
            System.out.println("EL USUARIO NO ES UN PROVEEDOR");

            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Compra> actualizarCategoria(@PathVariable Integer id, @RequestBody Compra c) {
        Compra comp = serviceImpl.findById(id);
        if (comp != null) {
            try {
                if (usuarioController.validarProveedor(c.getProveedor().getId_usuario())) {
                    comp.setGanancia(c.getGanancia());
                    comp.setProducto(c.getProducto());
                    comp.setProveedor(c.getProveedor());
                    productoController.actualizarPVP(c.getProducto().getId_producto(), c.getGanancia());
//                    iec.actualizar(1);

                    return new ResponseEntity<>(serviceImpl.save(comp), HttpStatus.CREATED);
                } else {
                    System.out.println("EL USUARIO NO ES UN PROVEEDOR");

                    return new ResponseEntity<>(HttpStatus.CONFLICT);

                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Compra> eliminarCategoria(@PathVariable Integer id
    ) {
        serviceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
