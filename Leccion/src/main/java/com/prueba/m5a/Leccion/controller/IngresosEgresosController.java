/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.controller;

import com.prueba.m5a.Leccion.model.Compra;
import com.prueba.m5a.Leccion.model.IngresosEgresos;
import com.prueba.m5a.Leccion.service.IngresosEgresosServiceImpl;
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
@RestController
@RequestMapping("/IngresosEgresos")
public class IngresosEgresosController {

    @Autowired
    IngresosEgresosServiceImpl serviceImpl;

    @Autowired
    CompraController compraController;

    @Autowired
    VentaController ventaController;

    @Operation(summary = "Se obtiene la lista de personas")
    @GetMapping("/get")
    public ResponseEntity<List<IngresosEgresos>> lista() {
        IngresosEgresos ingegr = serviceImpl.findById(1);
        double in = ventaController.ingresos(),
                eg = compraController.egresos(),
                total= in- eg;
        System.out.println(in+" - "+eg+" = "+total);
        if (ingegr != null) {
            try {
                ingegr.setIngresos(in);
                ingegr.setEgresos(eg);
                ingegr.setTotal(total);
//                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
//
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(serviceImpl.findByAll(), HttpStatus.OK);

    }

    @PostMapping("/post")
    public ResponseEntity<IngresosEgresos> crear(@RequestBody IngresosEgresos p
    ) {
        return new ResponseEntity<>(serviceImpl.save(p), HttpStatus.CREATED);
    }
    //    public ResponseEntity<IngresosEgresos> actualizar(@PathVariable Integer id, @RequestBody IngresosEgresos p) {

//    public boolean actualizar() {
//        IngresosEgresos ingegr = serviceImpl.findById(1);
//        double in = ventaController.ingresos(),
//                eg = compraController.egresos();
//        if (ingegr != null) {
//            try {
//                ingegr.setIngresos(in);
//                ingegr.setEgresos(eg);
//                ingegr.setTotal(in - eg);
//                return true;
//            } catch (Exception e) {
//                return false;
//            }
//
//        } else {
//            return false;
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<IngresosEgresos> elimiar(@PathVariable Integer id) {
//        serviceImpl.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
