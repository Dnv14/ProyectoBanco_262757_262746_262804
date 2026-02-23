package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoClienteDTO;
import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClientesDAO implements IClientesDAO{
    
    private static final Logger LOGGER = Logger.getLogger(ClientesDAO.class.getName());
    
    @Override
    public Cliente crearCliente(NuevoClienteDTO nuevoCliente) throws PersistenciaException {
        try {
            String codigoSQL = """
                               INSERT INTO Cliente(nombreCompleto, usuario, fechaNacimiento, domicilio,
                               contrasenia)
                               VALUES(?, ?, ?, ? , ?);
                               """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
        } catch (Exception e) {
        }return null;
    }

    @Override
    public boolean validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws PersistenciaException {
        try {
            String codigoSQL = """
                                           SELECT 1
                                           FROM clientes
                                           WHERE usuario = ? AND contrasenia = ?;
                                           """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            comando.setString(1,usuarioCliente.getUsuario());
            comando.setString(2, usuarioCliente.getContrasenia());
            
            ResultSet resultado = comando.executeQuery();

            boolean acceso = resultado.next(); 
            
            resultado.close();
            comando.close();
            conexion.close();
            
            return acceso;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al validar el acceso del usuario.",ex);
        }
        
    }
    
}
