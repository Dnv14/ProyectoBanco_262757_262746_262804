/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

            comando.setInt(1, nuevaOpreacionDTO.getMonto());
            comando.setString(2, formatoFechaHora);
            comando.setString(4, nuevaOpreacionDTO.getNumeroCuenta());

            comando.execute();
            comando.executeUpdate();

            ResultSet idsOperacion = comando.getGeneratedKeys();

            if (idsOperacion.next()) {
              idOperacion = idsOperacion.getInt(1);
            }
            
            LOGGER.fine("Se registró la operación");

            return new Operacion(idOperacion, nuevaOpreacionDTO.getMonto(), nuevaOpreacionDTO.getFechaHora(), nuevaOpreacionDTO.getTipoOperacion(), nuevaOpreacionDTO.getNumeroCuenta());

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible registrar la operación", ex);
        }
    }

}
