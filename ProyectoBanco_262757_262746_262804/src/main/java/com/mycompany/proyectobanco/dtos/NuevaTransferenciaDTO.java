/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.dtos;

/**
 *
 * @author Diego
 */
public class NuevaTransferenciaDTO {

    private Integer idOperacion;
    private String cuentaDestino;

    public NuevaTransferenciaDTO (Integer idOperacion, String cuentaDestino) {
        this.idOperacion = idOperacion;
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }
    
    
}
