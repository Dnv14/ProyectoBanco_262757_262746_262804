/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;

/**
 *
 * @author Diego
 */
public interface IOperacionBO {
    public abstract Operacion realizarOperacion(NuevaOperacionDTO nuevaOperacionDTO)throws NegocioException;
    public abstract void actualizarSaldoCuentaOrigen(NuevaOperacionDTO operacionDTO) throws NegocioException;
}
