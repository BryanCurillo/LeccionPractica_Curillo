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
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bryan
 */
@Entity
public class IngresosEgresos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_IngreEgre")
    private int id_IngreEgre;

    @Getter
    @Setter
    @Column(name = "ingresos")
    private Double Ingresos;
    
    @Getter
    @Setter
    @Column(name = "egresos")
    
    private Double Egresos;
    @Getter
    @Setter
    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    private Venta venta;

}
