/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.dtos;

import java.util.GregorianCalendar;

/**
 *
 * @author BALAMRUSH
 */
public class NuevaOperacionDTO {
    private Integer monto;
    private GregorianCalendar fechaHora;
    private String tipoOperacion;
    private String numeroCuenta;

    public NuevaOperacionDTO(Integer monto, GregorianCalendar fechaHora, String tipoOperacion, String numeroCuenta) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getMonto() {
        return monto;
    }

    public GregorianCalendar getFechaHora() {
        return fechaHora;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    
}
