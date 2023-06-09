/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.m5a.Leccion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Bryan
 */
@Data
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private int id_compra;

//    @NotBlank(message = "Ingrese el porcentaje de ganancia que va a tener al vender el producto")
    @Column(name = "ganancia")
    private double ganancia;

//    @NotBlank(message = "Ingrese una contraseña")   
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_usuario")
    private Usuario proveedor;

//    @NotBlank(message = "Ingrese una contraseña")
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @JsonIgnore
    @OneToMany(mappedBy = "compra")
    private List<IngresosEgresos> listsEgresos;
}
