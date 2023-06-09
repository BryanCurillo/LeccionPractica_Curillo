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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Bryan
 */
@Data
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int id_venta;

//    @NotBlank(message = "Ingrese la cantidad que se vende")
    @Column(name = "cantidad")
    private int cantidad;

//    @NotBlank(message = "Ingrese el precio de venta del producto")
//    @Column(name = "precio_PVP")
//    private double precio_PVP;
//    @NotBlank(message = "Ingrese la fecha en la que se realiza la venta")
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_venta;

//    @NotBlank(message = "Ingrese el id del cliente")
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_usuario")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto productoVenta;

//    @ManyToOne
//    @JoinColumn(name = "id_IngreEgre", referencedColumnName = "id_IngreEgre")
//    private IngresosEgresos ie;
    @PrePersist
    protected void onCreate() {
        fecha_venta = new Date();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "venta")
    private List<IngresosEgresos> listsIngresos;
}
