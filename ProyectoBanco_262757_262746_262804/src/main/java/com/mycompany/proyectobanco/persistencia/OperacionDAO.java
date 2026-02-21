/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Operacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author BALAMRUSH
 */
public class OperacionDAO implements IOperacionDAO {

    private static final Logger LOGGER = Logger.getLogger(OperacionDAO.class.getName());

    @Override
    public Operacion realizarOperacion(NuevaOperacionDTO nuevaOpreacionDTO) throws PersistenciaException {
        try {
            int idOperacion = 0;
            String codigoSQL = """
                               INSERT INTO Operacion(monto, fechaHora, numeroCuenta)
                               values(?,?,?);
                               """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);

            LocalDateTime fechaHora = LocalDateTime.now();
            DateTimeFormatter formatterFechaHora = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String formatoFechaHora = fechaHora.format(formatterFechaHora);

            comando.setLong(1, nuevaOpreacionDTO.getMonto());
            comando.setString(2, formatoFechaHora);
            comando.setString(3, nuevaOpreacionDTO.getNumeroCuenta());

            comando.execute();

            ResultSet idsOperacion = comando.getGeneratedKeys();

            if (idsOperacion.next() == true) {
                idOperacion = idsOperacion.getInt(1);
            }

            LOGGER.fine("Se registró la operación");

            return new Operacion(idOperacion, nuevaOpreacionDTO.getMonto(), nuevaOpreacionDTO.getFechaHora(), nuevaOpreacionDTO.getNumeroCuenta());

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible registrar la operación", ex);
        }
    }

    @Override
    public void actualizarSaldoCuentaOrigen(NuevaOperacionDTO operacionDTO) throws PersistenciaException {
        try {
            Cuenta cuentaOrigen = null;
            ICuentasDAO cuentasDAO = new CuentasDAO();
            List<Cuenta> cuentasBanco = cuentasDAO.consultarCuentasActivas();

            for (Cuenta cuenta : cuentasBanco) {
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(operacionDTO.getNumeroCuenta())) {
                    cuentaOrigen = cuenta;
                }
            }

            Long saldoNuevo = cuentaOrigen.getSaldo() - operacionDTO.getMonto();
            String codigoSQL = """
                                UPDATE cuenta
                                SET saldo = ?
                                WHERE idCliente = ? AND numeroCuenta = ?;
                              """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            comando.setLong(1, saldoNuevo);
            comando.setLong(2, cuentaOrigen.getIdCliente());
            comando.setString(3, cuentaOrigen.getNumeroCuenta());

            comando.execute();
            comando.close();
            conexion.close();

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible actualizar el saldo de la cuenta origen.", ex);
        }
    }

    @Override
    public List<Operacion> consultarOperaciones() throws PersistenciaException {
        try {
            List<Operacion> operaciones = new LinkedList<>();
            String codigoSQL = """
                                           SELECT idOperacion, monto, fechaHora, numeroCuenta
                                           FROM Operacion;
                                                          """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                LocalDateTime fechaHora = rs.getTimestamp("fechaHora").toLocalDateTime();
                Operacion operacion = new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        fechaHora,
                        rs.getString("numeroCuenta"));
                operaciones.add(operacion);
            }
            comando.close();
            conexion.close();

            return operaciones;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las operaciones", ex);
        }
    }

}
