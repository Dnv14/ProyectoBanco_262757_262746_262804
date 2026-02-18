package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;

/**
 *
 * @author Diego
 */
public interface IClientesDAO {
    public abstract Cliente crearCliente(NuevoClienteDTO nuevoCliente) throws PersistenciaException;
}
