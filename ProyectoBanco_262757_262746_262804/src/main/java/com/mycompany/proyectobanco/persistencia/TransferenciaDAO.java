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
            Cuenta cuentaOrigen = null;
            ICuentasDAO cuentasDAO = new CuentasDAO();
            List<Cuenta> cuentasBanco = cuentasDAO.consultarCuentasActivas();

            Operacion cuentaDestino = null;
            IOperacionDAO operacionesDAO = new OperacionDAO();
            List<Operacion> operaciones = operacionesDAO.consultarOperaciones();
            
            for (Cuenta cuenta : cuentasBanco) {
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(transferenciaDTO.getCuentaDestino())) {
                    cuentaOrigen = cuenta;
                }
            }

            Long saldoNuevo = cuentaOrigen.getSaldo() + ;
            String codigoSQL = """
                                UPDATE cuenta
                                SET saldo = ?
                                WHERE idCliente = ? AND numeroCuenta = ?;
                              """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            comando.setLong(1, saldoNuevo);
            comando.setString(2, cuentaOrigen.getIdCliente());
            comando.setString(3, cuentaOrigen.getNumeroCuenta());
        }

    }

    
    
}

//CREATE TABLE Transferencia (
//  idOperacion INT PRIMARY KEY NOT NULL,
//  cuentaDestino VARCHAR(16) NOT NULL,
//  FOREIGN KEY (idOperacion) REFERENCES Operacion(idOperacion),
//  FOREIGN KEY (cuentaDestino) REFERENCES Cuenta(numeroCuenta)
//);
