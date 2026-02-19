
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Cuenta;
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
public class CuentasDAO implements ICuentasDAO{

    private static final Logger LOGGER = Logger.getLogger(CuentasDAO.class.getName());
    
    @Override
    public List<Cuenta> consultarCuentasCliente(Integer idCliente) throws PersistenciaException {
        try {
            List<Cuenta> cuentasCliente = new LinkedList<>();
            String codigoSQL = """
                                                      SELECT numeroCuenta, estado, fechaApertura, saldo, idCliente
                                                      FROM cuenta
                                                      WHERE idCliente = 1
                                           """; //Aqui se cambiara a que sea el id del cliente que inicio sesion, para poder probar se dejo el idCliente = 1
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet rs = comando.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(
                    rs.getString("numeroCuenta"),
                    Cuenta.Estado.valueOf(rs.getString("estado")),
                    convertirFecha(rs.getDate("fechaApertura")),
                    rs.getLong("saldo"),
                    String.valueOf(rs.getLong("idCliente"))
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
                                           FROM cuenta
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
                    String.valueOf(rs.getLong("idCliente"))
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

    
    
}
