/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Operacion;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IHistorialOperacionesBO {
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta)throws NegocioException;
    public List<Operacion> consultarTransferenciaCuenta(String numeroCuenta)throws NegocioException;
    public List<Operacion> consultarRetirosPorCuenta(String numeroCuenta)throws NegocioException;
    
  
}
