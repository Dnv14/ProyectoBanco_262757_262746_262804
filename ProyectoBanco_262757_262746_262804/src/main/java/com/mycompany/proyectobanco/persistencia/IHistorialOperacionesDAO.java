/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IHistorialOperacionesDAO {
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta)throws PersistenciaException;
    public List<Retiro> consultarRetirosCuenta(String numeroCuenta)throws PersistenciaException;
    public List<Transferencia> consultarTransferenciaCuenta(String numeroCuenta)throws PersistenciaException;
    
}
