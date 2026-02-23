/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.dtos;


import com.mycompany.proyectobanco.entidades.Cuenta;
import java.util.GregorianCalendar;

/**
 *
 * @author Diego
 */
public class NuevaCuentaDTO {
    private String numeroCuenta;
    private Cuenta.Estado estado;;
    private GregorianCalendar fechaApertura;
    private Long saldo;
    private Long idCliente;
    
    public NuevaCuentaDTO(String numeroCuenta, Cuenta.Estado estado, GregorianCalendar fechaApertura, Long saldo, Long idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cuenta.Estado getEstado() {
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
