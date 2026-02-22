/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Transferencia;
import com.mycompany.proyectobanco.persistencia.HistorialOperacioneDAO;
import com.mycompany.proyectobanco.persistencia.IHistorialOperacionesDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class HistorialOperacionesBO implements IHistorialOperacionesBO{
    
    private IHistorialOperacionesDAO historialOperacionesDAO;

    public HistorialOperacionesBO(IHistorialOperacionesBO historialOperacionesDAO) {
        this.historialOperacionesDAO = new HistorialOperacioneDAO();
    }
    
    

    @Override
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta) throws NegocioException {
        if(numeroCuenta == null || numeroCuenta.isEmpty()){
            throw new NegocioException("No se ha seleccionado una cuenta", null);
        }
        try{
            return historialOperacionesDAO.consultarOperacionesCuenta(numeroCuenta);
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar las operaciones", ex);
            
        }
    }

    @Override
    public List<Retiro> consultarRetiroCuenta(String numeroCuenta) throws NegocioException {
        try{
            return historialOperacionesDAO.consultarRetirosCuenta(numeroCuenta);
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar el retiro", ex);
        }
    }

    @Override
    public List<Transferencia> consultarTrasnferenciaCuenta(String numeroCuenta) throws NegocioException {
        try{
            return historialOperacionesDAO.consultarTransferenciaCuenta(numeroCuenta);
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar las transacciones", ex);
        }
    }
    
}
