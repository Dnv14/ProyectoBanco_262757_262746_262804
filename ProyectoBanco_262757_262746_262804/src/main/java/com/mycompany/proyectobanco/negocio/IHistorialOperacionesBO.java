/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.HistorialOperacionesDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IHistorialOperacionesBO {
    List<HistorialOperacionesDTO> consultarHistorialOperaciones(String numeroCuenta, String tipo);
    List<String> obtenerNumerosCuenta() ;
    
        
   
}
