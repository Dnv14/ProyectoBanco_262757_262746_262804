/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.dtos;

import java.util.GregorianCalendar;

/**
 *
 * @author Diego
 */
public class NuevoClienteDTO {
    private String nombreCompleto;
    private String usuario;
    private GregorianCalendar fechaNacimiento;
    private String domicilio;
    private String contrasenia;

    public NuevoClienteDTO(String nombreCompleto, String usuario, GregorianCalendar fechaNacimiento, String domicilio, String contrasenia) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.contrasenia = contrasenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    
}
