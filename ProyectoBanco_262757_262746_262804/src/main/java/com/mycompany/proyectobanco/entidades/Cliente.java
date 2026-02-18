/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.entidades;

import java.util.GregorianCalendar;

/**
 *
 * @author Diego
 */
public class Cliente {

    private String idCliente;
    private String nombreCompleto;
    private String usuario;
    private GregorianCalendar fechaNacimiento;
    private String domicilio;
    private String contrasenia;

    public Cliente() {
    }

    public Cliente(String idCliente, String nombreCompleto, String usuario, GregorianCalendar fechaNacimiento, String domicilio, String contrasenia) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.contrasenia = contrasenia;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
