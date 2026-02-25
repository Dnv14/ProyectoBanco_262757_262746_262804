/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.HistorialOperacionesDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.negocio.IHistorialOperacionesBO;
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

    /**
     * Constructor que ocupa historialOperacionesDAO para poder conectarse a la BO
     * @param historialOperacionesDAO 
     */
    public HistorialOperacionesBO(IHistorialOperacionesDAO historialOperacionesDAO) {
        this.historialOperacionesDAO = historialOperacionesDAO;
    }

 
    /**
     * Recupera el historial de operaciones que se han realizado.
     * @param numeroCuenta
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta) throws NegocioException {
        try{
            return historialOperacionesDAO.consultarOperacionesCuenta(numeroCuenta);       
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar operaciones", ex);
        }
    }

 
    /**
     * Recupera el historial de transferencia que se han realizado.
     * @param numeroCuenta
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<Operacion> consultarTransferenciaCuenta(String numeroCuenta) throws NegocioException {
        try{
            return historialOperacionesDAO.consultarTrasnferenciaCuenta(numeroCuenta);       
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar transferencia", ex);
        }
    }


    /**
     * Recupera el historial de retiros que se han realizado.
     * @param numeroCuenta
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<Operacion> consultarRetirosPorCuenta(String numeroCuenta) throws NegocioException {
        try{
            return historialOperacionesDAO.consultarRetirosCuenta(numeroCuenta);       
        }catch(PersistenciaException ex){
            throw new NegocioException("Existe un error al consultar retiro", ex);
        }
    }
    
 
    
}
