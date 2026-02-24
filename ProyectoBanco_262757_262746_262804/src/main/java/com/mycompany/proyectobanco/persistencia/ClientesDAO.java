package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoClienteDTO;
import com.mycompany.proyectobanco.dtos.ValidarUsuarioClienteDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClientesDAO implements IClientesDAO {

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
        }
        return null;
    }

    @Override
    public Cliente validarClienteEstaRegistrado(ValidarUsuarioClienteDTO usuarioCliente) throws PersistenciaException {
        Cliente cliente = null;
        try {
            String codigoSQL = """
                                SELECT idCliente, nombreCompleto, usuario, fechaNacimiento, domicilio
                                FROM Clientes
                                WHERE usuario = ? AND contrasenia = ?;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            comando.setString(1, usuarioCliente.getUsuario());
            comando.setString(2, usuarioCliente.getContrasenia());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(String.valueOf(resultado.getLong("idCliente")));
                cliente.setNombreCompleto(resultado.getString("nombreCompleto"));
                cliente.setUsuario(resultado.getString("usuario"));

                Date fechaSQL = resultado.getDate("fechaNacimiento");
                GregorianCalendar fechaCalendar = new GregorianCalendar();
                fechaCalendar.setTime(fechaSQL);

                cliente.setFechaNacimiento(fechaCalendar);
                cliente.setDomicilio(resultado.getString("domicilio"));
            }

            resultado.close();
            comando.close();
            conexion.close();

            return cliente;

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al validar el acceso del usuario.", ex);
        }

    }

    @Override
    public List<String> obtenerTodasLasContrasenias() throws PersistenciaException {
        List<String> contrasenias = new LinkedList<>();
        try {
            String codigoSQL = """
                               SELECT contrasenia
                               FROM Clientes;
                               """;

            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                contrasenias.add(resultado.getString("contrasenia"));
            }
            resultado.close();
            comando.close();
            conexion.close();

            return contrasenias;

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al obtener las contraseñas de los clientes", ex);
        }
    }

}
