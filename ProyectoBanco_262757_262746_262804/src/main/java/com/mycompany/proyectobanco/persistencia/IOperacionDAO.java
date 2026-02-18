/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;

/**
 *
 * @author BALAMRUSH
 */
public interface IOperacionDAO {
    public abstract Operacion realizarOperacion(NuevaOperacionDTO nuevaOpreacionDTO)throws PersistenciaException;
}
