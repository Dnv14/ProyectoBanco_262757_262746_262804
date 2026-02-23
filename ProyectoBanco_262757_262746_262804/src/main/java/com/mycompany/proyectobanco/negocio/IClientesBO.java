
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;

/**
 *
 * @author Julian
 */
public interface IClientesBO {
    public abstract boolean validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws NegocioException;
}
