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
import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Bryan
 */
@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private int id_persona;

//    @NotBlank(message = "Ingrese su nombre")
    @Column(name = "nombre")
    private String nombre;

//    @NotBlank(message = "Ingrese su apellido")
    @Column(name = "apellido")
    private String apellido;

//    @NotBlank(message = "Ingrese su numero de cedula")
    @Size(max = 10, message = "Ingrese correctamente su cedula de identidad")
    @Column(name = "cedula")
    private String cedula;

//    @NotBlank(message = "Ingrese la direccion de su domicilio")
    @Column(name = "direccion")
    private String direccion;

//    @NotBlank(message = "Ingrese su numero de telefono")
    @Size(max = 10, message = "Ingrese correctamente su numero de telefono")
    @Column(name = "telefono")
    private String telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "persona")
    private List<Usuario> listUsuario;
}
