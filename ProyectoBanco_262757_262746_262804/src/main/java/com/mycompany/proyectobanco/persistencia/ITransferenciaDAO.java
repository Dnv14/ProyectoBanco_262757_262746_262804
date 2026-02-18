/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaTransferenciaDTO;
import com.mycompany.proyectobanco.entidades.Transferencia;

/**
 *
 * @author Diego
 */
public interface ITransferenciaDAO {
    public abstract Transferencia crearTransferencia (NuevaTransferenciaDTO nuevaTransferencia)throws PersistenciaException;
}
