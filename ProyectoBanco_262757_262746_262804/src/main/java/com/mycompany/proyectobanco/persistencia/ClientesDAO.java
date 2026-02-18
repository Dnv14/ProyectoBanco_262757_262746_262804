package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
}
