/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author BALAMRUSH
 */
public class NuevaOperacionDTO {
    private Integer monto;
    private LocalDateTime fechaHora;
    private String numeroCuenta;

    public NuevaOperacionDTO(Integer monto, LocalDateTime fechaHora, String numeroCuenta) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    
}
