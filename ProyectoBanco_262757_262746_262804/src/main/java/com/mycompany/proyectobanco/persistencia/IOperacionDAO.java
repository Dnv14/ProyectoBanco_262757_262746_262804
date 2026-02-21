/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IOperacionDAO {
    public abstract Operacion realizarOperacion(NuevaOperacionDTO nuevaOperacionDTO)throws PersistenciaException;
    public abstract void actualizarSaldoCuentaOrigen(NuevaOperacionDTO operacionDTO) throws PersistenciaException;
    public List<Operacion> consultarOperaciones() throws PersistenciaException;
    
}
