/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IHistorialOperacionesBO {
    List<Operacion> consultarOperacionesCuenta(String numeroCuenta)throws NegocioException;
    List<Retiro> consultarRetiroCuenta(String numeroCuenta)throws NegocioException;
    List<Transferencia> consultarTrasnferenciaCuenta(String numeroCuenta)throws NegocioException;
        
   
}
