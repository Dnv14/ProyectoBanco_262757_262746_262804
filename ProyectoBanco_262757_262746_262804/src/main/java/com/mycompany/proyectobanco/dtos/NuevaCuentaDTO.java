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
public class NuevaCuentaDTO {
    
    public enum Estado{
        ACTIVO,INACTIVO
    }
    
    private String numeroCuenta;
    private Estado estado;
    private GregorianCalendar fechaApertura;
    private Long saldo;
    private Long idCliente;

    public NuevaCuentaDTO(String numeroCuenta, Estado estado, GregorianCalendar fechaApertura, Long saldo, Long idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Estado getEstado() {
        return estado;
    }

    public GregorianCalendar getFechaApertura() {
        return fechaApertura;
    }

    public Long getSaldo() {
        return saldo;
    }

    public Long getIdCliente() {
        return idCliente;
    }
    
    

 
}
