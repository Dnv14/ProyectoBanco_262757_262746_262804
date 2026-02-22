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
public class HistorialOperacionesDTO {
    private Integer idOperacion;
    private long monto;
    private LocalDateTime fechaHora;
    private String tipo;

    public HistorialOperacionesDTO(Integer idOperacion, long monto, LocalDateTime fechaHora, String tipo) {
        this.idOperacion = idOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public long getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
}
