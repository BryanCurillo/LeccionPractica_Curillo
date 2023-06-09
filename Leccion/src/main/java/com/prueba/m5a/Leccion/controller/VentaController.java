/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Venta;
import com.prueba.m5a.Leccion.service.VentaServiceImpl;
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
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaServiceImpl serviceImpl;

    @Autowired
    ProductoController productoController;

    @Autowired
    UsuarioController usuarioController;

//    @Autowired
//    IngresosEgresosController iec;

    @Operation(summary = "Se obtiene lista de Categorias")
    @GetMapping("/get")
    public ResponseEntity<List<Venta>> lista() {
        return new ResponseEntity<>(serviceImpl.findByAll(), HttpStatus.OK);
    }

    public Double ingresos() {
        double ingresos = 0;
        for (Venta v : serviceImpl.findByAll()) {
            System.out.println("CANTIDAD INGRESOS = "+v.getCantidad());
            System.out.println("PVP = "+productoController.ProductoXid(v.getProductoVenta().getId_producto()).getPrecio_PVP());
            
            ingresos = ingresos + (v.getCantidad() * productoController.ProductoXid(v.getProductoVenta().getId_producto()).getPrecio_PVP());

        }
        return ingresos;
    }

    @Operation(summary = "Debe enviar los campos del Rol")
    @PostMapping("/post")
    public ResponseEntity<Venta> crear(@RequestBody Venta v) {

        if (usuarioController.validarCliente(v.getCliente().getId_usuario()) && productoController.actualizarInv(v.getProductoVenta().getId_producto(), v.getCantidad())) {
//            iec.actualizar(1);
            return new ResponseEntity<>(serviceImpl.save(v), HttpStatus.CREATED);
        } else {
            System.out.println("EL USUARIO NO ES UN CLIENTE");
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Integer id, @RequestBody Venta v) {
        Venta venta = serviceImpl.findById(id);
        if (venta != null) {
            try {
                venta.setCantidad(v.getCantidad());
                venta.setFecha_venta(v.getFecha_venta());
//                venta.setPrecio_PVP(v.getPrecio_PVP());
                venta.setProductoVenta(v.getProductoVenta());
                venta.setCliente(v.getCliente());
                if (usuarioController.validarCliente(v.getCliente().getId_usuario()) && productoController.actualizarInv(venta.getProductoVenta().getId_producto(), venta.getCantidad())) {
//                    iec.actualizar(1);
                    return new ResponseEntity<>(serviceImpl.save(venta), HttpStatus.CREATED);
                } else {
                    System.out.println("EL USUARIO NO ES UN CLIENTE");
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
    public ResponseEntity<Venta> eliminar(@PathVariable Integer id) {
        serviceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
