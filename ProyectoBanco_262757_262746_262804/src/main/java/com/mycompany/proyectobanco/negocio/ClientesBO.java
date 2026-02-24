
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import com.mycompany.proyectobanco.persistencia.IClientesDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;

/**
 *
 * @author Diego
 */
public class ClientesBO implements IClientesBO{
    
    private final IClientesDAO clientesDAO;
    
    /**
     * constructor para poder inicializar la BO usando los clientes DAO para 
     * acceder a metodos
     * @param clientesDAO 
     */
    public ClientesBO(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }
    
    
    /**
     * validamos que el cliente este dentro del registro de la base de datis
     * usando los getters de la DTO para poder acceder a estos datos
     * @param usuarioCliente
     * @return
     * @throws NegocioException 
     */
    @Override
    public Cliente validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws NegocioException {
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
            Cliente cliente = clientesDAO.validarClienteEstaRegistrado(usuarioCliente);
            if(cliente == null){
                throw new NegocioException("Usuario no encontrado.",null);
            }
            return cliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al intentar validar el usuario.",ex);
        }
    }
    
}
