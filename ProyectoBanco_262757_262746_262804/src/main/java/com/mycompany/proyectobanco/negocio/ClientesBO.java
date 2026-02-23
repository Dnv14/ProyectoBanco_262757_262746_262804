
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.persistencia.IClientesDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;

/**
 *
 * @author Diego
 */
public class ClientesBO implements IClientesBO{
    
    private final IClientesDAO clientesDAO;

    public ClientesBO(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }
    
    

    @Override
    public boolean validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws NegocioException {
        if(usuarioCliente.getContrasenia() == null){
            throw new NegocioException("La contrasenia no debe estar vacia.",null);
        }
        if(usuarioCliente.getContrasenia().length() > 255){
            throw new NegocioException("La contrasenia debe tener maximo 255 caracteres.",null);
        }
        if(usuarioCliente.getUsuario() == null){
            throw new NegocioException("El usuario no debe estar vacio.",null);
        }
        try {
            boolean acceso = clientesDAO.validarClienteEstaRegistrado(usuarioCliente);
            return acceso;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al intentar validar el usuario.",ex);
        }
    }
    
}
