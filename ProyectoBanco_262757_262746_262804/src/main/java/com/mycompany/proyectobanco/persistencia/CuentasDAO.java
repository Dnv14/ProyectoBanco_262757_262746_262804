package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaCuentaDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Cuenta.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    
    /**
     * Consultamos las cuentas de un cliente por su id, asi trayendo todas las cuentas asociadas 
     * a este
     * @param idCliente: cliente el cual se requiere saber sus cuentas asociadas.
     * @return
     * @throws PersistenciaException 
     */
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
    
    /**
     * Convierte la fecha de sql a un gregorianCalendar
     * seteando el tiempo y retornandolo.
     * @param fechaSQL
     * @return 
     */
    private GregorianCalendar convertirFecha(java.sql.Date fechaSQL) {
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.setTime(fechaSQL);
        return calendario;
    }

    /**
     * Consultamos las cuentas activas registradas en las bases de datos
     * devolviendo una lista para para poder manipular estas mismas y
     * validar que puedan hacer movimientos.
     * @return
     * @throws PersistenciaException 
     */
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
    
    /**
     * Actualiza el estado de una cuenta, de Activo a Inactivo y viceversa, recibiendo el estado actual y el numero de cuuenta
     * @param estado: estado actual para poder ser canbiado
     * @param numeroCuenta: numero de la cuenta, para poder saber que cuenta mover su estado
     * @throws PersistenciaException 
     */
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
    
    /**
     * Consultamos el estado de cuenta para poder asi validar
     * y confirmar como se cambiara el estado de cuenta con ayuda del metodo
     * anterior
     * @param numeroCuenta
     * @return
     * @throws PersistenciaException 
     */
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

    /**
     * Crea una cuenta, utilizando una cuentaDTO para poder llenar todos los datos del insert, 
     * validando antes que el cliente este en la base de datos
     * @param cuentaDTO: cuenta la cual se agarran los datos para crear la nueva cuenta
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Cuenta crearCuenta(NuevaCuentaDTO cuentaDTO) throws PersistenciaException {
        try {
            String comandoSQL = """
                                     INSERT INTO Cuentas(numeroCuenta, estado, fechaApertura, saldo, idCliente)
                                     VALUES(? ,? , current_date() , ?, ?);
                                     """;

            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);

            //SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd-MM-yyyy");
            //String fechaString = formateadorFecha.format(cuentaDTO.getFechaApertura().getTime());

            comando.setString(1, cuentaDTO.getNumeroCuenta());
            comando.setString(2, cuentaDTO.getEstado().name());
            comando.setLong(3, cuentaDTO.getSaldo());
            comando.setLong(4, cuentaDTO.getIdCliente());

            comando.execute();
            conexion.close();
            comando.close();

            return new Cuenta(cuentaDTO.getNumeroCuenta(), cuentaDTO.getEstado(), cuentaDTO.getFechaApertura(), cuentaDTO.getSaldo(), cuentaDTO.getIdCliente());

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("no se pudo crear la cuenta", ex);
        }
    }
    
    /**
     * Devuelve una lista de todos los numeros de cuenta existentes en la base de datos
     * usado principalmente para poder crear la cuenta y generar el numero aleatorio
     * ayudando a validar que el numero de cuenta no exista previamente.
     * @param numeroCuenta: recibe el parametros del numero de cuenta que se origino para poder comparar si existe o no.
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public List<String> consultarCuentaNumero(String numeroCuenta) throws PersistenciaException {
        try {
            List<String> cuentasCliente = new LinkedList<>();
            String codigoSQL = """
                               SELECT numeroCuenta                                                     
                               FROM Cuentas
                               """;

            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet resultadoConsulta = comando.executeQuery();

            while (resultadoConsulta.next()) {
                String numeroCuentaConsulta = resultadoConsulta.getString("numeroCuenta");
                cuentasCliente.add(numeroCuentaConsulta);
            }
            comando.close();
            conexion.close();

            return cuentasCliente;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas del cliente.", ex);
        }
    }

}
//CREATE TABLE Cuentas (
//  numeroCuenta VARCHAR(16) PRIMARY KEY,
//  estado ENUM ('ACTIVO','INACTIVO') NOT NULL,
//  fechaApertura DATE NOT NULL,
//  saldo BIGINT NOT NULL,
//  idCliente BIGINT NOT NULL,
//  FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente)
//);
