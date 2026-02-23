package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoClienteDTO;
import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IClientesDAO {
    public abstract Cliente crearCliente(NuevoClienteDTO nuevoCliente) throws PersistenciaException;
    public abstract Cliente validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws PersistenciaException;
    public List<String> obtenerTodasLasContrasenias() throws PersistenciaException; 
}
