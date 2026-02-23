
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;

/**
 *
 * @author Julian
 */
public interface IClientesBO {
    public abstract Cliente validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws NegocioException;
}
