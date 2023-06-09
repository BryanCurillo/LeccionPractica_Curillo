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
//import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Bryan
 */
@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id_producto;

//    @NotBlank(message = "Ingrese el nombre del producto")
    @Column(name = "nombre")
    private String nombre;

//    @NotBlank(message = "Ingrese el precio al por mayor del producto")
    @Column(name = "precio_emp")
    private double precio_emp;

    @Column(name = "precio_PVP")
    private double precio_PVP;

    @Column(name = "imagen")
    private String imagen;

//    @NotBlank(message = "Ingrese la descripcion del producto")
    @Column(name = "descripcion")
    private String descripcion;

//    @NotBlank(message = "Ingrese la cantidad de producto que se esta adquiriendo")
    @Column(name = "cantidad")
    private int cantidad;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<Compra> listProductos;

    @JsonIgnore
    @OneToMany(mappedBy = "productoVenta")
    private List<Venta> listProductosVent;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
}
