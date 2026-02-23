/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;


import com.mycompany.proyectobanco.entidades.Operacion;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IHistorialOperacionesDAO {
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta)throws PersistenciaException;
    public List<Operacion> consultarTrasnferenciaCuenta(String numeroCuenta)throws PersistenciaException;
    public List<Operacion> consultarRetirosCuenta(String numeroCuenta)throws PersistenciaException;
    
}
