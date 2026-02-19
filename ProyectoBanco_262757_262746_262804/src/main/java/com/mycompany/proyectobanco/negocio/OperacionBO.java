/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;

/**
 *
 * @author BALAMRUSH
 */
public class OperacionBO implements IOperacionBO {
    
    private final IOperacionDAO operacionDAO;

    public OperacionBO(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO ;
    }
    
    @Override
    public Operacion realizarOperacion(NuevaOperacionDTO nuevaOperacionDTO) throws NegocioException {
        if (nuevaOperacionDTO.getNumeroCuenta()== null) {
            throw new NegocioException("El numero de cuenta es obligatorio", null);
        }
        
        if (nuevaOperacionDTO.getMonto() <= 0) {
            throw new NegocioException("parametros de monto invalidos ", null);
        }
        
        
        try {
            Operacion operacion = this.operacionDAO.realizarOperacion(nuevaOperacionDTO);
            return operacion;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear la operacion", ex);
        }
    }

    
    
}
