package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Cuenta.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class CuentasDAO implements ICuentasDAO {

    private static final Logger LOGGER = Logger.getLogger(CuentasDAO.class.getName());

    @Override
    public List<Cuenta> consultarCuentasCliente(Long idCliente) throws PersistenciaException {
        try {
            List<Cuenta> cuentasCliente = new LinkedList<>();
            String codigoSQL = """
                                                      SELECT numeroCuenta, estado, fechaApertura, saldo, idCliente
                                                      FROM Cuentas
                                                      WHERE idCliente = ?
                                           """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setLong(1, idCliente);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta(
                        rs.getString("numeroCuenta"),
                        Cuenta.Estado.valueOf(rs.getString("estado")),
                        convertirFecha(rs.getDate("fechaApertura")),
                        rs.getLong("saldo"),
                        rs.getLong("idCliente")
                );
                cuentasCliente.add(cuenta);
            }
            comando.close();
            conexion.close();

            return cuentasCliente;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas del cliente.", ex);
        }
    }

    private GregorianCalendar convertirFecha(java.sql.Date fechaSQL) {
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.setTime(fechaSQL);
        return calendario;
    }

    @Override
    public List<Cuenta> consultarCuentasActivas() throws PersistenciaException {
        try {
            List<Cuenta> cuentasCliente = new LinkedList<>();
            String codigoSQL = """
                                           SELECT numeroCuenta, estado, fechaApertura, saldo, idCliente
                                           FROM Cuentas
                                           WHERE estado = 'ACTIVO';
                                                          """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(
                        rs.getString("numeroCuenta"),
                        Cuenta.Estado.valueOf(rs.getString("estado")),
                        convertirFecha(rs.getDate("fechaApertura")),
                        rs.getLong("saldo"),
                        rs.getLong("idCliente")
                );
                cuentasCliente.add(cuenta);
            }
            comando.close();
            conexion.close();

            return cuentasCliente;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas activas del banco.", ex);
        }
    }

    @Override
    public void actualizarEstadoCuenta(String estado, String numeroCuenta) throws PersistenciaException {
        try {
            String codigoSQL = """
                               UPDATE Cuentas 
                               SET estado = ? 
                               WHERE numeroCuenta = ? ;
                               """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, estado);
            comando.setString(2, numeroCuenta);
//            comando.setLong(3, idCliente);

            comando.executeUpdate();
            comando.close();
            conexion.close();
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("no se pudo cambiar el estado de cuenta", ex);
        }

    }

    @Override
    public String consultarEstadoCuenta(String numeroCuenta) throws PersistenciaException {
        try {
            String estado = null;
            String codigoSQL = """
                               SELECT estado 
                               FROM Cuentas                                                    
                               WHERE numeroCuenta = ?;
                               """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, numeroCuenta);
//            comando.setLong(2, idCliente);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                estado = rs.getString("estado");
                return estado;
            }
            comando.close();
            conexion.close();
            return estado;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas del cliente.", ex);
        }

    }

}
