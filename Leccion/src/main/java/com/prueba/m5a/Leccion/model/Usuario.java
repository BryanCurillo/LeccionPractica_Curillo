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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Bryan
 */
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id_usuario;

    @NotBlank(message = "Ingrese su correo electronico")
    @Email(message = "Ingrese una dirección de correo válida")
    @Column(name = "correo")
    private String correo;

    @NotBlank(message = "Ingrese una contraseña")
//    @Size(min = 3, max = 15, message = "El usuario debe tener entre 3 y 15 caracteres")
    @Column(name = "clave")
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    private List<Compra> listProv;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venta> listClientes;

}
