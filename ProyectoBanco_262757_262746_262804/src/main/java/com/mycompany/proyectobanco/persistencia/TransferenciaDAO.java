package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaTransferenciaDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class TransferenciaDAO implements ITransferenciaDAO {

    private static final Logger LOGGER = Logger.getLogger(TransferenciaDAO.class.getName());

    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaDTO nuevaTransferencia) throws PersistenciaException {
        try {
            String codigoSQL = """
                               INSERT INTO Transferencia(idOperacion, cuentaDestino)
                               VALUES (?, ?);
                               """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            comando.setInt(1, nuevaTransferencia.getIdOperacion());
            comando.setString(2, nuevaTransferencia.getCuentaDestino());
            comando.execute();

            comando.close();
            conexion.close();
            return new Transferencia(nuevaTransferencia.getIdOperacion(), nuevaTransferencia.getCuentaDestino());

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("N se pudo realizar la transferencia", ex);
        }
    }

    @Override
    public void actualizarSaldoCuentaDestino(NuevaTransferenciaDTO transferenciaDTO) throws PersistenciaException {
        try {
            Cuenta cuentaDestino = null;
            ICuentasDAO cuentasDAO = new CuentasDAO();
            List<Cuenta> cuentasBanco = cuentasDAO.consultarCuentasActivas();

            Operacion operacionTransferencia = null;
            IOperacionDAO operacionesDAO = new OperacionDAO();
            List<Operacion> operaciones = operacionesDAO.consultarOperaciones();

            for (Operacion operacion : operaciones) {
                if (operacion.getIdOperacion() == transferenciaDTO.getIdOperacion()) {
                    operacionTransferencia = operacion;
                    break;
                }
                

            }

            for (Cuenta cuenta : cuentasBanco) {
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(transferenciaDTO.getCuentaDestino())) {
                    cuentaDestino = cuenta;
                    break;
                }
            }

           
            Long saldoNuevo = cuentaDestino.getSaldo() + operacionTransferencia.getMonto();
            String codigoSQL = """
                                UPDATE cuenta
                                SET saldo = ?
                                WHERE idCliente = ? AND numeroCuenta = ?;
                              """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            comando.setLong(1, saldoNuevo);
            comando.setLong(2, cuentaDestino.getIdCliente());
            comando.setString(3, cuentaDestino.getNumeroCuenta());

            comando.executeUpdate();
            comando.close();
            conexion.close();

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible actualizar el saldo de la cuenta destino.", ex);
        }

    }

}

