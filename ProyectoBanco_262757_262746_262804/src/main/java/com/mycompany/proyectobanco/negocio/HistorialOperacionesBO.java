/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.HistorialOperacionesDTO;
import com.mycompany.proyectobanco.negocio.IHistorialOperacionesBO;
import com.mycompany.proyectobanco.persistencia.HistorialOperacioneDAO;
import com.mycompany.proyectobanco.persistencia.IHistorialOperacionesDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BALAMRUSH
 */
public class HistorialOperacionesBO implements IHistorialOperacionesBO{
    
    private IHistorialOperacionesDAO historialOperacionesDAO;

    public HistorialOperacionesBO() {
        this.historialOperacionesDAO = new HistorialOperacioneDAO();
    }


    @Override
    public List<HistorialOperacionesDTO> consultarHistorialOperaciones(String numeroCuenta, String tipo){                                                            
        try { 
            return historialOperacionesDAO.consultarOperacionesCuenta(numeroCuenta, tipo);
        } catch (PersistenciaException ex) {
            Logger.getLogger(HistorialOperacionesBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  List.of();
    }  



    @Override
    public List<String> obtenerNumerosCuenta(){       
        try {   
            return historialOperacionesDAO.obtenerNumerosCuenta();
        } catch (PersistenciaException ex) {
            Logger.getLogger(HistorialOperacionesBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return List.of();
    }
    
    
 
    
}
