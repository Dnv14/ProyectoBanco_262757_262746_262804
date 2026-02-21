/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.entidades;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;

/**
 *
 * @author BALAMRUSH
 */
public class Operacion {
    private int idOperacion;
    private long monto;
    private LocalDateTime fechaHora;
    private String numeroCuenta;

    public Operacion() {
    }

    public Operacion(int idOperacion, long monto, LocalDateTime fechaHora, String numeroCuenta) {
        this.idOperacion = idOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    
}
